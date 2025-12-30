package main.services;

import main.exceptions.*;
import main.models.PetModel;

public class ValidarPet {

    final String NA = "NÃO INFORMADO";

    public String validarNome(String name) {

        if (!name.matches("^$|^[A-Za-zÀ-ÿ]+ [A-Za-zÀ-ÿ]+$")) {
            throw new InvalidNameException();
        }
        if (name.isEmpty()) {
            name = NA;
        }

        return name;
    }

    public PetModel.PetType validarTipo(String tipo) {

        if (tipo == null || tipo.isBlank()) {
            throw new InvalidTypeException();
        }

        if (tipo.equalsIgnoreCase("gato")) return PetModel.PetType.GATO;
        if (tipo.equalsIgnoreCase("cao")
                || tipo.equalsIgnoreCase("cão")
                || tipo.equalsIgnoreCase("cachorro")) return PetModel.PetType.CAO;

        throw new InvalidTypeException();
    }

    public PetModel.PetGender validarGenero(String genero) {

        if (genero == null || genero.isBlank()) {
            throw new InvalidGenderException();
        }

        if (genero.equalsIgnoreCase("f")) return PetModel.PetGender.F;
        if (genero.equalsIgnoreCase("m")) return PetModel.PetGender.M;

        throw new InvalidGenderException();
    }


    public String validarEndereco(String endereco) {

        if (!endereco.matches("^$|^[A-Za-zÀ-ÿ0-9\\s\\.]+,\\s*\\d+[A-Za-z0-9\\s\\-]*,\\s*[A-Za-zÀ-ÿ\\s]+$")) {
            throw new InvalidAddressException();
        }
        if (endereco.isEmpty()) {
            endereco = NA;
        }

        return endereco;
    }

    public String validarIdade(String idade) {

        if (idade.contains(",")) {
            idade = idade.replace(",", ".");
        }

        if (!idade.isEmpty()) {
            if (Double.parseDouble(idade) > 20 || Double.parseDouble(idade) < 0.1) {
                throw new InvalidAgeException();
            }
        }

        if (idade.isEmpty()) {
            idade = NA;
        }

        return idade;
    }

    public String validarPeso(String peso) {

        if (peso.contains(",")) {
            peso = peso.replace(",", ".");
        }

        if (!peso.isEmpty()) {
            if (Double.parseDouble(peso) > 60 || Double.parseDouble(peso) < 0.5) {
                throw new InvalidWeightException();
            }
        }

        if (peso.isEmpty()) {
            peso = NA;
        }

        return peso;
    }

    public String validarRaca(String raca) {

        if (!raca.matches("^$|^[A-Za-zÀ-ÿ\\s'-]+$")) {
            throw new InvalidRaceException();
        }

        if (raca.isEmpty()) {
            raca = NA;
        }

        return raca;
    }
}
