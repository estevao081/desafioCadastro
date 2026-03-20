package main.services.form;

import java.util.List;
import java.util.Scanner;

public class FormUtils {

    private final String pathFormulario;
    private final int indicePrimeiraPerguntaAlteravel;
    private final Scanner scan;

    public FormUtils(String pathFormulario, Scanner scan) {
        this.pathFormulario = pathFormulario;
        this.scan = scan;
        this.indicePrimeiraPerguntaAlteravel = 7;
    }

    public String getPathFormulario() {
        return pathFormulario;
    }

    public int getIndicePrimeiraPerguntaAlteravel() {
        return indicePrimeiraPerguntaAlteravel;
    }

    public boolean validarPerguntasAlteraveis(List<String> perguntas) {

        if (perguntas.size() <= getIndicePrimeiraPerguntaAlteravel()) {
            System.out.println("Sem perguntas para alterar!");
            return false;
        }
        return true;
    }

    public void exibirPerguntasAlteraveis(List<String> perguntas) {

        System.out.println("Informe o índice da pergunta:");

        for (int i = getIndicePrimeiraPerguntaAlteravel(); i < perguntas.size(); i++) {
            System.out.println(perguntas.get(i));
        }
    }

    public int obterIndiceAlteracao(List<String> perguntas) {

        String campo = scan.nextLine();
        int indiceLinha = Integer.parseInt(campo) - 1;

        if (indiceLinha < getIndicePrimeiraPerguntaAlteravel() ||
                indiceLinha >= perguntas.size()) {
            System.out.println("ERRO: Índice inválido!");
            return -1;
        }

        return indiceLinha;
    }
}
