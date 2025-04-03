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

    public void addPet() {

        Scanner scan = new Scanner(System.in);

        List<QuestionList> ql = questionListRepository.findAll();
        List<String> answers = new ArrayList<>();

        for (QuestionList question : ql) {
            System.out.println(question.toString());
            answers.add(scan.nextLine());
        }

        Pet pet = Pet.PetBuilder.petBuilder()
                .name(new PetName(answers.getFirst().toUpperCase()))
                .type(typeFormat(PetType.valueOf(answers.get(1).toUpperCase())))
                .gender(genderFormat(PetGender.valueOf(answers.get(2).toUpperCase())))
                .addres(new PetAddres(answers.get(3).toUpperCase()))
                .age(new PetAge(Integer.parseInt(answers.get(4))))
                .weight(new PetWeight(Double.parseDouble(answers.get(5))))
                .race(new PetRace(answers.get(6).toUpperCase()))
                .build();
        petRepository.save(pet);
        System.out.println(pet);
    }

    public String typeFormat(PetType petType) {
        String type = petType.toString();
        PetType[] petTypeList = PetType.values();
        List<String> typeFound = new ArrayList<>();
        if (Arrays.toString(petTypeList).contains(type)) {
            typeFound.add(type);
        }
        return typeFound.toString().replace("[", "").replace("]", "");
    }

    public String genderFormat(PetGender petGender) {
        String gender = petGender.toString();
        PetGender[] petGenderList = PetGender.values();
        List<String> genderFound = new ArrayList<>();
        if (Arrays.toString(petGenderList).contains(gender)) {
            genderFound.add(gender);
        }
        return genderFound.toString().replace("[", "").replace("]", "");
    }
}