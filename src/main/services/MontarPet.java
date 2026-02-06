package main.services;

import main.models.Pet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MontarPet {

    public Pet montar(LerFormulario lerFormulario, String pathFormulario) {

        Scanner scan = new Scanner(System.in);

        //Lista para as respostas do usuário
        List<String> respostas = new ArrayList<>();

        //Lê as perguntas do formulário
        List<String> perguntas = lerFormulario.ler(pathFormulario);

        //Salva na lista as respostas do usuário
        for (String pergunta : perguntas) {
            System.out.println(pergunta);
            respostas.add(scan.nextLine());
        }

        Pet pet = new Pet();
        pet.setName(respostas.getFirst());
        pet.setType(respostas.get(1));
        pet.setGender(respostas.get(2));
        pet.setAddress(respostas.get(3));
        pet.setAge(respostas.get(4));
        pet.setWeight(respostas.get(5));
        pet.setRace(respostas.get(6));

        return pet;
    }
}
