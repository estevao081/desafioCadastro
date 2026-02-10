package main.services.pet;

import main.models.Pet;

import java.util.List;
import java.util.Scanner;

public class MontarPet {

    private static final int CAMPOS_PADRAO = 7;

    public Pet montar(
            RespostasUsuario respostasUsuario,
            LerFormulario lerFormulario,
            String pathFormulario,
            Scanner scan) {

        List<String> respostas = respostasUsuario.respostas(lerFormulario, pathFormulario, scan);

        Pet pet = new Pet();

        pet.setName(respostas.get(0));
        pet.setType(respostas.get(1));
        pet.setGender(respostas.get(2));
        pet.setAddress(respostas.get(3));
        pet.setAge(respostas.get(4));
        pet.setWeight(respostas.get(5));
        pet.setRace(respostas.get(6));

        for (int i = CAMPOS_PADRAO; i < respostas.size(); i++) {
            pet.addAtributoExtra(respostas.get(i).toUpperCase());
        }

        return pet;
    }
}
