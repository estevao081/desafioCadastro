package main.repositories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FormFileRepository implements FormRepository {

    private final String pathPets;

    public FormFileRepository(
            String pathPets
            ) {
        this.pathPets = pathPets;
    }

    @Override
    public void adicionar(String resposta) {

        try (var paths = Files.walk(Paths.get(pathPets))){
            paths
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".TXT"))
                    .forEach(filePath -> {
                        try {

                            List<String> linhas = Files.readAllLines(filePath);

                            linhas.add("EXTRA - " + resposta);

                            Files.write(filePath, linhas);

                            System.out.println("Formulário alterado com sucesso!");

                        } catch (IOException e) {
                            System.err.println("ERRO: " + e.getMessage());
                        }
                    });

        } catch (IOException e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }

    @Override
    public void alterar() {

    }

    @Override
    public void excluir() {

    }
}
