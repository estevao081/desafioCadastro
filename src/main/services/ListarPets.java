package main.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ListarPets {

    public static void listarTodosOsPets(String path) {

        try {
            Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".TXT"))
                    .forEach(filePath -> {
                        try {
                            List<String> linhas = Files.readAllLines(filePath);
                            linhas.forEach(System.out::println);
                            System.out.println("--------------------");
                        } catch (IOException e) {
                            System.err.println("ERRO: " + e.getMessage());
                        }
                    });
        } catch (IOException e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }
}
