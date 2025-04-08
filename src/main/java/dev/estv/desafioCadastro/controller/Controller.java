package dev.estv.desafioCadastro.controller;

import dev.estv.desafioCadastro.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
public class Controller {

    @Autowired
    AddPetService addPetService;
    @Autowired
    AlterPetService alterPetService;
    @Autowired
    DeletePetService deletePetService;
    @Autowired
    ListPetsService listPetsService;
    @Autowired
    FindPetService findPetService;

    @PostMapping("/pets")
    public void addPet() {
        addPetService.addPet();
    }

    @PutMapping("/pets{id}")
    public void alterPet() {
        alterPetService.alterPet();
    }

    @DeleteMapping("/pets{id}")
    public void deletePet() {
        deletePetService.deletePet();
    }

    @GetMapping("/pets")
    public void listPets() {
        listPetsService.listPets();
    }

    public void findPet() {
        findPetService.findPet();
    }
}