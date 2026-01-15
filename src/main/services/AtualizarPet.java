package main.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class AtualizarPet {

    public static void alterarPet(String path) {

        Scanner scanner = new Scanner(System.in);
        GerarNome gerarNome = new GerarNome();

        //Utiliza o metodo buscarPet para encontrar o pet com as informações a serem atualizadas
        List<String> pets = BuscarPet.buscarPet(path);

        if (pets.isEmpty()) {
            System.out.println("Nenhum pet encontrado.");
            return;
        }

        //Seleciona o pet com base no índice mostrado no console
        System.out.println("Informe o número do pet que deseja alterar:");
        int opcaoPet = Integer.parseInt(scanner.nextLine()) - 1;

        if (opcaoPet < 0 || opcaoPet >= pets.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        //Remove o índice dos dados do pet
        String petSelecionado = pets.get(opcaoPet)
                .replaceFirst("^\\d+\\. ", "");

        List<String> campos = List.of(
                "Nome",
                "Idade",
                "Peso",
                "Raça",
                "Endereço"
        );

        System.out.println("Qual campo deseja alterar?");
        for (int i = 0; i < campos.size(); i++) {
            System.out.println((i + 1) + " - " + campos.get(i));
        }

        int campo = Integer.parseInt(scanner.nextLine()) - 1;

        if (campo < 0 || campo >= campos.size()) {
            System.out.println("Campo inválido.");
            return;
        }

        System.out.println("Informe o novo valor:");
        String novoValor = scanner.nextLine().toUpperCase();

        try {
            Files.walk(Paths.get(path))
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
                                        gerarNome.gerarNome(novoValor + ".txt");

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
