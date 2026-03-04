package main.services;

import main.models.Campos;
import main.models.Pet;
import main.models.PetFiltro;
import main.services.form.AtualizarForm;
import main.services.form.MenuForm;
import main.services.pet.*;

import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {

    public static void exibirMenu(String pathFormulario, String pathPets, Scanner scan,
                                  MontarPet montarPet, GerarNome gerarNome, RespostasUsuario respostasUsuario,
                                  LerFormulario lerFormulario, AtualizarForm atualizarForm, ValidarNumero validarNumero,
                                  PetService petService, PercorrerArquivos percorrerArquivos) {

        List<String> perguntas = List.of(
                "1 - Iniciar o sistema para cadastro de PETS",
                "2 - Iniciar o sistema para alterar formulário",
                "3 - Encerrar"
        );

        perguntas.forEach(System.out::println);

        String opcao = scan.nextLine();

        switch (opcao) {
            case "1" -> MenuPet.exibirMenuPet(pathFormulario, pathPets, scan, montarPet, gerarNome,
                    respostasUsuario, lerFormulario, validarNumero, petService, percorrerArquivos);
            case "2" -> MenuForm.exibirMenuForm(scan, pathFormulario, atualizarForm);
            case "3" -> System.out.println("Encerrando");
        }
    }
}
