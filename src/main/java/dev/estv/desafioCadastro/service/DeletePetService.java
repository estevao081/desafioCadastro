package dev.estv.desafioCadastro.service;

import dev.estv.desafioCadastro.model.Pet;
import dev.estv.desafioCadastro.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class DeletePetService {

    @Autowired
    FindPetService findPetService;
    @Autowired
    PetRepository petRepository;

    public void deletePet() {

        List<Pet> petsFound = findPetService.findPet();

        if(!petsFound.isEmpty()) {

            System.out.println("Informe o ID do pet que deseja deletar:");
            Scanner scan = new Scanner(System.in);
            String id = scan.nextLine();

            if (id.isEmpty() || !id.matches("^-?[0-9]{1,19}$")) {
                throw new IllegalArgumentException("ERRO: O campo ID só pode conter números e não pode estar em branco.");
            }

            System.out.println("Deseja realmente deletar as informações do pet cadastrado? S/N");
            String answer = scan.nextLine();

            if(!answer.matches("^[sSnN]$")) {
                throw new IllegalArgumentException("ERRO: Digite apenas 'S' para sim ou 'N' para não.\n");
            }

            if(answer.toUpperCase().matches("S")) {
                petRepository.deleteById(Long.valueOf(id));
                System.out.println("\nPet deletado com sucesso.\n");
            }
        }
    }
}