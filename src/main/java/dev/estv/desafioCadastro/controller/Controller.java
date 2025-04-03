package dev.estv.desafioCadastro.controller;

import dev.estv.desafioCadastro.service.AddPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pets")
public class Controller {

    @Autowired
    AddPetService addPetService;

    @PostMapping("/pets")
    public void addPet() { addPetService.addPet(); }
}
