package dev.estv.desafioCadastro.service;

import dev.estv.desafioCadastro.model.Pet;
import dev.estv.desafioCadastro.model.PetGender;
import dev.estv.desafioCadastro.model.PetType;
import dev.estv.desafioCadastro.model.QuestionList;
import dev.estv.desafioCadastro.repository.PetRepository;
import dev.estv.desafioCadastro.repository.QuestionListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class AddPetService {

    @Autowired
    QuestionListRepository questionListRepository;
    @Autowired
    PetRepository petRepository;
    @Autowired
    PetValidate petValidate;

    Scanner scan = new Scanner(System.in);

    public void addPet() {

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
        String age = answers.get(4);
        String weight = answers.get(5);
        String race = answers.get(6).toUpperCase();

        Pet pet = Pet.PetBuilder.petBuilder()
                .name(petValidate.name(name))
                .type(petValidate.type(EnumFormat.typeFormat(PetType.valueOf(type))))
                .gender(petValidate.gender(EnumFormat.genderFormat(PetGender.valueOf(gender))))
                .addres(petValidate.addres(addres))
                .age(petValidate.age(age))
                .weight(petValidate.weight(weight))
                .race(petValidate.race(race))
                .build();

        System.out.println(pet);
        petRepository.save(pet);
        System.out.println("\nInformações do Pet salvas com sucesso.\n");
    }
}