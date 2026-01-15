package main.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class RemoverPet {

    public static void deletarPet(String path) {

        Scanner scanner = new Scanner(System.in);

        //Busca por algum critério o pet a ser deletado
        List<String> pets = BuscarPet.buscarPet(path);

        if (pets.isEmpty()) {
            System.out.println("Nenhum pet encontrado.");
            return;
        }

        int opcaoPet;

        while (true) {
            try {
                //Seleciona o pet com base no índice mostrado no console
                System.out.println("Informe o número do pet que deseja deletar:");
                opcaoPet = Integer.parseInt(scanner.nextLine()) - 1;

                if (opcaoPet < 0 || opcaoPet >= pets.size()) {
                    System.out.println("Número inválido. Tente novamente.");
                    continue;
                }
                break;

            } catch (NumberFormatException e) {
                System.out.println("Número inválido. Tente novamente.");
            }
        }

        String petSelecionado = pets.get(opcaoPet)
                .replaceFirst("^\\d+\\. ", "");

        System.out.println("Confirma a exclusão do pet? (S/N)");
        String confirmacao = scanner.nextLine();

        if (!confirmacao.equalsIgnoreCase("S")) {
            System.out.println("Exclusão cancelada.");
            return;
        }

        try {
            Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".TXT"))
                    .forEach(file -> {
                        try {
                            List<String> linhas = Files.readAllLines(file);

                            if (linhas.toString().equalsIgnoreCase(petSelecionado)) {
                                Files.delete(file);
                                System.out.println("Pet deletado com sucesso!");
                            }
                        } catch (IOException e) {
                            System.err.println("ERRO: " + e.getMessage());
                        }
                    });

        } catch (IOException e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }
}
