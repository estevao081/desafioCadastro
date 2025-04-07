package dev.estv.desafioCadastro.controller;

import dev.estv.desafioCadastro.service.AddPetService;
import dev.estv.desafioCadastro.service.AlterPetService;
import dev.estv.desafioCadastro.service.ListPetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
public class Controller {

    @Autowired
    AddPetService addPetService;
    @Autowired
    ListPetsService listPetsService;
    @Autowired
    AlterPetService alterPetService;

    @PostMapping("/pets")
    public void addPet() {
        addPetService.addPet();
    }

    @PutMapping("/pets")
    public void alterPet() {
        alterPetService.alterPet();
    }

    @GetMapping("/pets")
    public void searchPet() {
        listPetsService.listPets();
    }
}