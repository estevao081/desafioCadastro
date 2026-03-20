package main.services.form;

import main.repositories.FormRepository;

import java.util.List;
import java.util.Scanner;

public class MenuForm {

    private final FormService formService;

    public MenuForm(FormService formService) {
        this.formService = formService;
    }

    public void exibirMenuForm(Scanner scan) {

        while (true) {

            List<String> perguntasForm = List.of(
                    "1.Criar nova pergunta",
                    "2.Alterar pergunta existente",
                    "3.Excluir pergunta existente",
                    "4.Voltar para o menu inicial",
                    "5.Sair"
            );

            perguntasForm.forEach(System.out::println);

            String opcaoForm = scan.nextLine();

            switch (opcaoForm) {
                case "1" -> criar(scan);
                case "2" -> System.out.println("b");
                case "3" -> System.out.println("c");
                case "4" -> System.out.println("d");
                case "5" -> {
                    System.out.println("Encerrando");
                    return;
                }
            }
        }
    }

    private void criar(Scanner scan) {

        System.out.println("Digite a pergunta que deseja adicionar ao formulário:");

        String resposta = scan.nextLine();

        formService.adicionar(resposta);
    }
}
