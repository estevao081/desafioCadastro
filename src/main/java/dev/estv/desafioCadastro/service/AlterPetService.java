package dev.estv.desafioCadastro.service;

import dev.estv.desafioCadastro.model.Pet;
import dev.estv.desafioCadastro.model.QuestionList;
import dev.estv.desafioCadastro.repository.PetRepository;
import dev.estv.desafioCadastro.repository.QuestionListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class AlterPetService {

    @Autowired
    PetRepository petRepository;
    @Autowired
    QuestionListRepository questionListRepository;

    public void alterPet() {

        System.out.println("Informe os dados (Nome/Tipo/Idade/...) do pet que deseja alterar:");

        Scanner dataScan = new Scanner(System.in);
        String answer = dataScan.next().toUpperCase();

        List<Pet> allPets = petRepository.findAll();
        List<Pet> petFound = new ArrayList<>();

        for (Pet pet : allPets) {
            if (pet.toString().contains(answer)) {
                petFound.add(pet);
                System.out.println(pet);
            }
        }

        if (petFound.isEmpty()) {
            System.out.println("Não há pets cadastrados.");
        } else {
            System.out.println("Informe o ID do pet que deseja alterar:");
            Scanner scan = new Scanner(System.in);
            String id = scan.nextLine();

            if(id.isEmpty()) {
                throw new IllegalArgumentException("ERRO: O campo ID não pode ser nulo.");
            }

            if (petFound.toString().contains(id)) {
                System.out.println("a");
            }
        }
    }
}
