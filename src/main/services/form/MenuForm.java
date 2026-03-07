package main.services.form;

import java.util.List;
import java.util.Scanner;

public class MenuForm {

    public void exibirMenuForm(Scanner scan, String pathFormulario, AtualizarForm atualizarForm) {

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
                case "1" -> FormService.adicionarPergunta(scan, pathFormulario, atualizarForm);
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
}
