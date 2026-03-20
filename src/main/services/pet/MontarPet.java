package main.services.pet;

import main.models.Pet;

import java.util.List;
import java.util.Scanner;

public class MontarPet {

    private final RespostasUsuario respostasUsuario;
    private final LerFormulario lerFormulario;
    private final String pathFormulario;

    public MontarPet(
            RespostasUsuario respostasUsuario,
            LerFormulario lerFormulario,
            String pathFormulario) {
        this.respostasUsuario = respostasUsuario;
        this.lerFormulario = lerFormulario;
        this.pathFormulario = pathFormulario;
    }

    private static final int CAMPOS_PADRAO = 7;

    public Pet montar(Scanner scan) {

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
            pet.addAtributoExtra(
                    "[EXTRA - "
                            + lerFormulario.ler(pathFormulario).get(i).substring(12)
                            + "] - " + respostas.get(i).toUpperCase());
        }

        return pet;
    }
}
