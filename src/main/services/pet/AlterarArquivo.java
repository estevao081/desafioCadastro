package main.services.pet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class AlterarArquivo {

    public void alterar(String pathPets, String petSelecionado, int campo, String novoValor, GerarNome gerarNome) {
        try {
            Files.walk(Paths.get(pathPets))
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".TXT"))
                    .forEach(filePath -> {
                        try {
                            List<String> linhas = Files.readAllLines(filePath);

                            if (!linhas.toString().equalsIgnoreCase(petSelecionado)) return;

                            linhas.set(campo, novoValor);
                            Files.write(filePath, linhas);

                            //Atualiza o nome do arquivo caso o nome do pet seja alterado
                            if (campo == 0) {
                                String novoNomeArquivo =
                                        gerarNome.gerar(novoValor + ".txt");

                                Path novoPath = filePath.resolveSibling(novoNomeArquivo);

                                Files.move(filePath, novoPath);
                            }

                            System.out.println("Pet alterado com sucesso!");

                        } catch (IOException e) {
                            System.err.println("ERRO: " + e.getMessage());
                        }
                    });

        } catch (IOException e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }
}
