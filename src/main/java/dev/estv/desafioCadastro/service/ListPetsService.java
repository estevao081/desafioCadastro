package dev.estv.desafioCadastro.service;

import dev.estv.desafioCadastro.model.Pet;
import dev.estv.desafioCadastro.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListPetsService {

    @Autowired
    PetRepository petRepository;

    public void listPets() {

        List<Pet> pets = petRepository.findAll();

        for(Pet pet : pets) {
            System.out.println(pet);
        }

        if(pets.isEmpty()) {
            System.out.println("Não existem pets cadastrados.");
        }
    }
}