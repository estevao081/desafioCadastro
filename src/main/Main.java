package main;

import main.repositories.FormFileRepository;
import main.repositories.FormRepository;
import main.repositories.PetFileRepository;
import main.repositories.PetRepository;
import main.services.MenuPrincipal;
import main.services.form.FormService;
import main.services.form.FormUtils;
import main.services.form.MenuForm;
import main.services.pet.*;

import java.io.File;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {

        //Paths
        final String pathFormulario = "src/main/forms/FORMULARIO.TXT";
        final String pathPets = "petsCadastrados";

        //Define onde os pets cadastrados serão salvos
        File diretorio = new File(pathPets);

        //Cria o arquivo caso não exista (Evita o erro FileNotFound)
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        //Construtores
        PetUtils petUtils = new PetUtils();
        Scanner scan = new Scanner(System.in);
        GerarNome gerarNome = new GerarNome();
        LerFormulario lerFormulario = new LerFormulario();
        FormUtils formUtils = new FormUtils(pathFormulario);
        RespostasUsuario respostasUsuario = new RespostasUsuario();
        FormRepository formRepository = new FormFileRepository(pathFormulario);
        FormService formService = new FormService(formRepository);
        MontarPet montarPet = new MontarPet(respostasUsuario, lerFormulario, pathFormulario);

        PetRepository petRepository = new PetFileRepository(
                gerarNome,
                petUtils,
                pathPets);

        PetService petService = new PetService(petRepository);

        MenuPet menuPet = new MenuPet(
                scan,
                petService,
                montarPet,
                petUtils);


        MenuForm menuForm = new MenuForm(
                formService,
                lerFormulario,
                scan,
                formUtils);

        MenuPrincipal menuPrincipal = new MenuPrincipal(scan, menuPet, menuForm);

        menuPrincipal.exibir();
    }
}
