package main;

import main.models.PetFiltro;
import main.repositories.PetFileRepository;
import main.repositories.PetRepository;
import main.services.MenuPrincipal;
import main.services.form.AtualizarForm;
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
        MontarPet montarPet = new MontarPet();
        GerarNome gerarNome = new GerarNome();
        LerFormulario lerFormulario = new LerFormulario();
        AtualizarForm atualizarForm = new AtualizarForm();
        RespostasUsuario respostasUsuario = new RespostasUsuario();
        PetRepository petRepository = new PetFileRepository(
                diretorio,
                gerarNome,
                petUtils,
                pathPets);

        PetService petService = new PetService(
                petRepository,
                montarPet,
                respostasUsuario,
                lerFormulario,
                pathFormulario,
                scan,
                petUtils);

        MenuPet menuPet = new MenuPet(scan, petService);

        MenuForm menuForm = new MenuForm();

        MenuPrincipal menuPrincipal = new MenuPrincipal(scan, menuPet, menuForm, atualizarForm, pathFormulario);

        menuPrincipal.exibir();
    }
}
