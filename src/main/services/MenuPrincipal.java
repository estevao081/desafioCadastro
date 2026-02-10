package main.services;

import main.services.form.AtualizarForm;
import main.services.form.MenuForm;
import main.services.pet.*;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {

    public static void exibirMenu(
            String pathFormulario,
            String pathPets,
            File diretorio,
            Scanner scan,
            MontarPet montarPet,
            GerarNome gerarNome,
            EscreverArquivo escreverArquivo,
            RespostasUsuario respostasUsuario,
            LerFormulario lerFormulario,
            AtualizarForm atualizarForm
    ) {

        List<String> perguntas = List.of(
                "1 - Iniciar o sistema para cadastro de PETS",
                "2 - Iniciar o sistema para alterar formulário",
                "3 - Encerrar"
        );

        perguntas.forEach(System.out::println);

        String opcao = scan.nextLine();

        switch (opcao) {

            case "1" -> MenuPet.exibirMenuPet(
                    pathFormulario,
                    pathPets,
                    diretorio,
                    scan,
                    montarPet,
                    gerarNome,
                    escreverArquivo,
                    respostasUsuario,
                    lerFormulario);
            case "2" -> MenuForm.exibirMenuForm(scan, pathFormulario, atualizarForm);
            case "3" -> System.out.println("Encerrando");
        }
    }
}
