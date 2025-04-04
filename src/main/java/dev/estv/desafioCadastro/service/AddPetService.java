package dev.estv.desafioCadastro.service;

import dev.estv.desafioCadastro.model.*;
import dev.estv.desafioCadastro.repository.PetRepository;
import dev.estv.desafioCadastro.repository.QuestionListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AddPetService {

    @Autowired
    QuestionListRepository questionListRepository;
    @Autowired
    PetRepository petRepository;

    private final String NA = "NÃO INFORMADO";

    public void addPet() {

        Scanner scan = new Scanner(System.in);

        List<QuestionList> ql = questionListRepository.findAll();
        List<String> answers = new ArrayList<>();

        for (QuestionList question : ql) {
            System.out.println(question.toString());
            answers.add(scan.nextLine());
        }

        String name = answers.getFirst().toUpperCase();
        String type = answers.get(1).toUpperCase();
        String gender = answers.get(2).toUpperCase();
        String addres = answers.get(3).toUpperCase();
        String age = answers.get(4).toUpperCase();
        String weight = answers.get(5).toUpperCase();
        String race = answers.get(6).toUpperCase();

        if (!name.matches("^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$")) {
            throw new IllegalArgumentException("ERRO: O campo NOME não pode conter caracteres especiais e não pode ficar em branco.");
        }

        if (!race.matches("^$|^[A-Za-zÀ-ÖØ-öø-ÿ\\\\s]+$")) {
            System.out.println("ERRO: O campo RAÇA não pode conter caracteres especiais, somente letras de A-Z.");
        }

        if(!type.contains(String.valueOf(PetType.AVE))
                && !type.contains(String.valueOf(PetType.CÃO))
                && !type.contains(String.valueOf(PetType.GATO))
                && !type.contains(String.valueOf(PetType.PEIXE))
                && !type.contains(String.valueOf(PetType.ROEDOR))
                && !type.contains(String.valueOf(PetType.RÉPTIL))) {
            throw new IllegalArgumentException("ERRO: O campo TIPO deve ser preenchido com um dos tipos informados.");
        }

        if(!gender.contains(String.valueOf(PetGender.F))
                && !gender.contains(String.valueOf(PetGender.M))) {
            throw new IllegalArgumentException("ERRO: O campo GÊNERO deve ser preenchido com um dos tipos informados.");
        }

        if(!age.matches("^$|^[0-9]{1,2}([.,][0-9]{1,2})?$")) {
            throw new IllegalArgumentException("ERRO: O campo IDADE deve conter apenas números.");
        }

        if(!weight.matches("^$|^[0-9]{1,2}([.,][0-9]{1,2})?$")) {
            throw new IllegalArgumentException("ERRO: O campo PESO deve conter apenas números.");
        }

        if(Double.parseDouble(age) > 20 || Double.parseDouble(age) < 0) {
            throw new IllegalArgumentException("ERRO: Informe uma idade válida.");
        }

        if(Double.parseDouble(weight) > 60 || Double.parseDouble(weight) < 0) {
            throw new IllegalArgumentException("ERRO: Informe um peso válido.");
        }

        if(addres.isEmpty()) {
            addres = NA;
        }

        if(age.isEmpty()) {
            age = NA;
        }

        if(weight.isEmpty()) {
            weight = NA;
        }

        if(race.isEmpty()) {
            race = NA;
        }

        Pet pet = Pet.PetBuilder.petBuilder()
                .name(name)
                .type(EnumFormat.typeFormat(PetType.valueOf(type)))
                .gender(EnumFormat.genderFormat(PetGender.valueOf(gender)))
                .addres(addres)
                .age(age)
                .weight(weight)
                .race(race)
                .build();
        petRepository.save(pet);
        System.out.println("Informações do Pet salvas com sucesso.");
    }
}