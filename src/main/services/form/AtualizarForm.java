package main.services.form;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class AtualizarForm {

    public void atualizar(Scanner scan, String pathFormulario) {

        System.out.println("Digite a pergunta que deseja adicionar ao formulário:");

        String resposta = scan.nextLine();

        try {
            Files.walk(Paths.get(pathFormulario))
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
}
