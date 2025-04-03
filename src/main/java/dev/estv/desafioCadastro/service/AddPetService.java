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

    Scanner scan = new Scanner(System.in);

    private final String na = "NÃO INFORMADO";

    public void addPet() {

        List<QuestionList> ql = questionListRepository.findAll();
        List<String> answers = new ArrayList<>();

        for (QuestionList question : ql) {
            System.out.println(question.toString());
            answers.add(scan.nextLine());
        }

        for(String answer : answers) {
            if(answer == null) {
                answer = na;
            }
        }

        PetName petName = new PetName(answers.getFirst());
        PetType petType = typeFormat(PetType.valueOf(answers.get(1).toUpperCase()));
        PetSex petSex = sexFormat(PetSex.valueOf(answers.get(2).toUpperCase()));
        PetAddres petAddres = new PetAddres(answers.get(3));
        PetAge petAge = new PetAge(answers.get(4));
        PetWeight petWeight = new PetWeight(answers.get(5));
        PetRace petRace = new PetRace(answers.get(6));

        Pet pet = Pet.PetBuilder.petBuilder()
                .name(petName)
                .type(petType)
                .sex(petSex)
                .addres(petAddres)
                .age(petAge)
                .weight(petWeight)
                .race(petRace)
                .build();
//        petRepository.save(pet);

        System.out.println(pet);
    }

    public PetType typeFormat(PetType petType) {
        String type = petType.toString();
        PetType[] petTypeList = PetType.values();
        List<String> typeFound = new ArrayList<>();
        if (Arrays.toString(petTypeList).contains(type)) {
            typeFound.add(type);
        }
        return PetType.valueOf(typeFound.toString().replace("[", "").replace("]",""));
    }

    public PetSex sexFormat(PetSex petSex) {
        String sex = petSex.toString();
        PetSex[] petSexList = PetSex.values();
        List<String> sexFound = new ArrayList<>();
        if (Arrays.toString(petSexList).contains(sex)) {
            sexFound.add(sex);
        }
        return PetSex.valueOf(sexFound.toString().replace("[", "").replace("]",""));
    }
}
