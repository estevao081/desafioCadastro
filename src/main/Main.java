package main;

import main.models.Campos;
import main.models.Pet;
import main.models.PetFiltro;
import main.repositories.PetFileRepository;
import main.repositories.PetRepository;
import main.services.MenuPrincipal;
import main.services.form.AtualizarForm;
import main.services.pet.*;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

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
        Scanner scan = new Scanner(System.in);
        MontarPet montarPet = new MontarPet();
        GerarNome gerarNome = new GerarNome();
        LerFormulario lerFormulario = new LerFormulario();
        AtualizarForm atualizarForm = new AtualizarForm();
        ValidarNumero validarNumero = new ValidarNumero();
        RespostasUsuario respostasUsuario = new RespostasUsuario();
        PercorrerArquivos percorrerArquivos = new PercorrerArquivos();
        PetRepository petRepository = new PetFileRepository(diretorio, gerarNome, percorrerArquivos);
        PetService petService = new PetService(petRepository);

        //Menu principal
        MenuPrincipal.exibirMenu(pathFormulario, pathPets, scan, montarPet, gerarNome, respostasUsuario, lerFormulario,
                atualizarForm, validarNumero, petService, percorrerArquivos);
    }
}
