package main.services;

import main.exceptions.*;
import main.models.PetModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PetService {

    LerFormularioService read = new LerFormularioService();

    public void salvarPet() {

        read.lerFormulario();

        List<String> respostas = read.getRespostas();

        PetModel pet = new PetModel();

        final String NA = "NÃO INFORMADO";
        String name = respostas.getFirst();
        String type = respostas.get(1);
        String gender = respostas.get(2);
        String address = respostas.get(3);
        String age = respostas.get(4);
        String weight = respostas.get(5);
        String race = respostas.get(6);

        if (!name.matches("^$|^[A-Za-zÀ-ÿ]+ [A-Za-zÀ-ÿ]+$")) {
            throw new InvalidNameException();
        }

        if (type.equalsIgnoreCase("gato")) {
            pet.setType(PetModel.PetType.GATO);
        }

        if (type.equalsIgnoreCase("cao")
                || type.equalsIgnoreCase("cão")
                || type.equalsIgnoreCase("cachorro")) {
            pet.setType(PetModel.PetType.CACHORRO);
        }

        if (gender.equalsIgnoreCase("f")) {
            pet.setGender(PetModel.PetGender.F);
        }

        if (gender.equalsIgnoreCase("m")) {
            pet.setGender(PetModel.PetGender.M);
        }

        if (!address.matches("^$|^[A-Za-zÀ-ÿ0-9\\s\\.]+,\\s*\\d+[A-Za-z0-9\\s\\-]*,\\s*[A-Za-zÀ-ÿ\\s]+$")) {
            throw new InvalidAddressException();
        }

        if (respostas.get(4).contains(",")) {
            age = respostas.get(4).replace(",", ".");
        }

        if (respostas.get(5).contains(",")) {
            weight = respostas.get(5).replace(",", ".");
        }

        if (!age.isEmpty()) {
            if (Double.parseDouble(age) > 20 || Double.parseDouble(age) < 0.1) {
                throw new InvalidAgeException();
            }
        }

        if (!weight.isEmpty()) {
            if (Double.parseDouble(weight) > 60 || Double.parseDouble(weight) < 0.5) {
                throw new InvalidWeightException();
            }
        }

        if (!race.matches("^$|^[A-Za-zÀ-ÿ\\s'-]+$")) {
            throw new InvalidRaceException();
        }

        if (name.isEmpty()) {
            name = NA;
        }

        if (race.isEmpty()) {
            race = NA;
        }

        if (address.isEmpty()) {
            address = NA;
        }

        if (weight.isEmpty()) {
            weight = NA;
        }

        if (age.isEmpty()) {
            age = NA;
        }

        pet.setName(name);
        pet.setAddress(address);
        pet.setAge(age);
        pet.setWeight(weight);
        pet.setRace(race);

        String path = "src/main/pets";
        File diretorio = new File(path);

        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        File arquivo = new File(diretorio, "teste");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            bw.write(pet.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
