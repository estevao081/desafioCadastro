package main.repositories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

        try (var paths = Files.walk(Paths.get(pathFormulario))) {
            paths
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".TXT"))
                    .forEach(filePath -> {
                        try {

                            List<String> linhas = Files.readAllLines(filePath);

                            int campo = linhas.size() + 1;

                            linhas.add(campo + " - EXTRA - " + pergunta);

                            Files.write(filePath, linhas);

                        } catch (IOException e) {
                            System.err.println("ERRO: " + e.getMessage());
                        }
                    });

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
            System.err.println("ERRO ao alterar arquivo: " + e.getMessage());
        }
    }

    @Override
    public void excluir() {


    }
}
