package dev.estv.desafioCadastro.controller;

import dev.estv.desafioCadastro.service.AddPetService;
import dev.estv.desafioCadastro.service.ListPetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pets")
public class Controller {

    @Autowired
    AddPetService addPetService;
    @Autowired
    ListPetsService listPetsService;

    @PostMapping("/pets")
    public void addPet() {
        addPetService.addPet();
    }

    @GetMapping("/pets")
    public void searchPet() {
        listPetsService.listPets();
    }
}