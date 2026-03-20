package main.services.form;

import main.services.pet.LerFormulario;

import java.util.List;
import java.util.Scanner;

public class MenuForm {

    private final FormService formService;
    private final LerFormulario lerFormulario;
    private final Scanner scan;
    private final FormUtils formUtils;

    public MenuForm(
            FormService formService,
            LerFormulario lerFormulario,
            Scanner scan,
            FormUtils formUtils
    ) {
        this.formService = formService;
        this.lerFormulario = lerFormulario;
        this.scan = scan;
        this.formUtils = formUtils;
    }

    public void exibirMenuForm() {

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
                case "1" -> adicionar();
                case "2" -> alterar();
                case "3" -> excluir();
                case "4" -> {
                    return;
                }
                case "5" -> {
                    System.out.println("Encerrando...");
                    System.exit(0);
                }
                default -> System.out.println("ERRO: Informe uma opção válida!");
            }
        }
    }

    private void adicionar() {

        System.out.println("Digite a pergunta que deseja adicionar ao formulário:");
        String pergunta = scan.nextLine();

        formService.adicionar(pergunta);
        System.out.println("Pergunta adicionada com sucesso!");
    }

    private void alterar() {

        try {
            List<String> perguntas = lerFormulario.ler(formUtils.getPathFormulario());

            if (!formUtils.validarPerguntasAlteraveis(perguntas)) {
                return;
            }

            formUtils.exibirPerguntasAlteraveis(perguntas);

            int indice = formUtils.obterIndiceAlteracao(perguntas);
            if (indice == -1) return;

            System.out.println("Digite a nova pergunta:");
            String novaPergunta = scan.nextLine();

            formService.alterar(indice, novaPergunta);
            System.out.println("Formulário atualizado com sucesso!");

        } catch (NumberFormatException e) {
            System.out.println("ERRO: Índice inválido!");
        }
    }

    private void excluir() {

        try {
            List<String> perguntas = lerFormulario.ler(formUtils.getPathFormulario());

            if (!formUtils.validarPerguntasAlteraveis(perguntas)) {
                return;
            }

            formUtils.exibirPerguntasAlteraveis(perguntas);

            int indice = formUtils.obterIndiceAlteracao(perguntas);
            if (indice == -1) return;

            formService.excluir(indice);
            System.out.println("Pergunta excluída com sucesso!");

        } catch (NumberFormatException e) {
            System.out.println("ERRO: Índice inválido!");
        }
    }
}
