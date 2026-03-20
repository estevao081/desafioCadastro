package main.repositories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FormFileRepository implements FormRepository {

    private final String pathFormulario;

    public FormFileRepository(
            String pathFormulario
    ) {
        this.pathFormulario = pathFormulario;
    }

    @Override
    public void adicionar(String pergunta) {

        Path arquivoFormulario = Paths.get(pathFormulario);

        try {
            List<String> linhas = Files.readAllLines(arquivoFormulario);

            int campo = linhas.size() + 1;

            linhas.add(campo + " - EXTRA - " + pergunta);

            Files.write(arquivoFormulario, linhas);

        } catch (IOException e) {
            System.err.println("ERRO: " + e.getMessage());
        }


    }

    @Override
    public void alterar(int campo, String novoValor) {

        Path arquivoFormulario = Paths.get(pathFormulario);

        try {
            List<String> linhas = Files.readAllLines(arquivoFormulario);
            linhas.set(campo, novoValor);
            Files.write(arquivoFormulario, linhas);

        } catch (IOException e) {
            System.err.println("ERRO ao alterar pergunta: " + e.getMessage());
        }
    }

    @Override
    public void excluir(int indicePergunta) {

        Path arquivoFormulario = Paths.get(pathFormulario);

        try {
            List<String> linhas = Files.readAllLines(arquivoFormulario);

            linhas.remove(indicePergunta);

            List<String> linhasAtualizadas = new ArrayList<>();

            for (int i = 0; i < linhas.size(); i++) {
                String pergunta = linhas.get(i);

                String textoPergunta = pergunta.substring(pergunta.indexOf("-") + 1).trim();

                String novaPergunta = " " + (i + 1) + " - " + textoPergunta;

                linhasAtualizadas.add(novaPergunta);
            }

            Files.write(arquivoFormulario, linhasAtualizadas);

        } catch (IOException e) {
            System.err.println("ERRO ao remover pergunta: " + e.getMessage());
        }
    }
}
