package dev.estv.desafioCadastro.service;

import dev.estv.desafioCadastro.model.Pet;
import dev.estv.desafioCadastro.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class FindPetService {

    @Autowired
    PetRepository petRepository;

    public List<Pet> findPet() {

        List<Pet> allPets = petRepository.findAll();
        List<Pet> petFound = new ArrayList<>();

        if (!petRepository.findAll().isEmpty()) {

            System.out.println("Informe os dados (Nome/Tipo/Idade/...) do pet que deseja encontrar:");

            Scanner dataScan = new Scanner(System.in);
            String answer = dataScan.next();

            for (Pet pet : allPets) {
                if (pet.toString().contains(answer.toUpperCase())) {
                    petFound.add(pet);
                }
            }

            if (petFound.isEmpty()) {
                System.out.println("Não encontrado\n.");
            } else {
                System.out.println("\nEncontrados:");
                for (Pet pet : petFound) {
                    System.out.println(pet.toString().replace("[", "").replace("]", ""));
                }
            }
        } else {
            System.out.println("Não existem pets cadastrados.\n");
        }
        return petFound;
    }
}