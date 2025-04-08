package dev.estv.desafioCadastro.service;

import dev.estv.desafioCadastro.model.Pet;
import dev.estv.desafioCadastro.model.QuestionList;
import dev.estv.desafioCadastro.repository.PetRepository;
import dev.estv.desafioCadastro.repository.QuestionListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class AlterPetService {

    @Autowired
    PetRepository petRepository;
    @Autowired
    QuestionListRepository questionListRepository;
    @Autowired
    PetValidate petValidate;
    @Autowired
    FindPetService findPetService;

    public void alterPet() {

        List<Pet> petsFound = findPetService.findPet();

        if (!petsFound.isEmpty()) {

            System.out.println("Informe o ID do pet que deseja alterar:");
            Scanner scan = new Scanner(System.in);
            String id = scan.nextLine();

            if (id.isEmpty() || !id.matches("^-?[0-9]{1,19}$")) {
                throw new IllegalArgumentException("ERRO: O campo ID só pode conter números e não pode estar em branco.");
            }

            Optional<Pet> optional = petRepository.findById(Long.valueOf(id));

            if (optional.isPresent()) {
                List<QuestionList> questionList = questionListRepository.findAll();
                questionList.remove(1);
                questionList.remove(1);
                List<String> answers = new ArrayList<>();

                for (QuestionList question : questionList) {
                    System.out.println(question.toString());
                    answers.add(scan.nextLine());
                }

                String name = answers.getFirst().toUpperCase();
                String addres = answers.get(1).toUpperCase();
                String age = answers.get(2);
                String weight = answers.get(3);
                String race = answers.get(4).toUpperCase();

                Pet pet = optional.get();
                pet.setName(petValidate.name(name));
                pet.setAddres(petValidate.addres(addres));
                pet.setAge(petValidate.age(age));
                pet.setWeight(petValidate.weight(weight));
                pet.setRace(petValidate.race(race));

                petRepository.saveAndFlush(pet);
                System.out.println("\nInformações do Pet salvas com sucesso.\n");
            }
        }
    }
}