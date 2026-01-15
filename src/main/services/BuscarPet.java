package main.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BuscarPet {

    public static List<String> buscarPet(String path) {

        Scanner scanner = new Scanner(System.in);

        //Lista de critérios para a busca
        List<String> opcoesMenu = List.of(
                "Como deseja buscar o pet?",
                "1 - Nome",
                "2 - Gênero",
                "3 - Idade",
                "4 - Peso",
                "5 - Raça",
                "6 - Endereço",
                "7 - Data de cadastro"
        );

        //Lista de perguntas com os dados do pet
        List<String> perguntasPet = List.of(
                "Informe o nome do pet:",
                "Informe o gênero do pet:",
                "Informe a idade do pet:",
                "Informe o peso do pet:",
                "Informe a raça do pet:",
                "Informe o endereço do pet:",
                "Informe a data de cadastro:"
        );

        //HashSet para não permitir dados duplicados
        Set<Integer> criterios = new HashSet<>();
        List<String> dadosDoPet = new ArrayList<>();

        System.out.println("Qual o tipo do animal? (Cão/Gato)");
        dadosDoPet.add(scanner.nextLine().toUpperCase());
        if (dadosDoPet.getFirst().equalsIgnoreCase("cão")
                || dadosDoPet.getFirst().equalsIgnoreCase("cao")
                || dadosDoPet.getFirst().equalsIgnoreCase("cachorro")) {
            dadosDoPet.set(0, "CAO");
        }

        //Loop para saber os critérios de busca
        while (true) {
            try {
                opcoesMenu.forEach(System.out::println);
                int opcao = Integer.parseInt(scanner.nextLine());
                if (opcao < 1 || opcao > 7) {
                    System.out.println("Informe uma opção válida");
                }
                criterios.add(opcao - 1);
                System.out.println("Adicionar mais um critério? (S/N)");
                if (scanner.nextLine().equalsIgnoreCase("n")) break;
            } catch (NumberFormatException e) {
                System.out.println("Informe uma opção válida");
            }
        }

        for (int c : criterios) {
            System.out.println(perguntasPet.get(c));
            dadosDoPet.add(scanner.nextLine().toUpperCase());
        }

        //HashSet para não permitir dados duplicados
        Set<String> petsEncontrados = new HashSet<>();
        //Contador para exibir os pets encontrados em ordem numérica
        AtomicInteger contador = new AtomicInteger(1);

        try {
            Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".TXT"))
                    .forEach(file -> {
                        try {
                            List<String> pet = Files.readAllLines(file);

                            boolean match = dadosDoPet.stream()
                                    .allMatch(dado -> pet.toString().toUpperCase().contains(dado));

                            if (match) {
                                petsEncontrados.add(contador + ". " + pet);
                                contador.getAndIncrement();
                            }
                        } catch (IOException e) {
                            System.err.println("ERRO: " + e.getMessage());
                        }
                    });
        } catch (IOException e) {
            System.err.println("ERRO: " + e.getMessage());
        }

        //Conversão de Set -> List para que a lista possa ser ordenada
        List<String> petsEncontradosList = new ArrayList<>(petsEncontrados);

        //Ordena a lista de pets encontrados para exibição
        Collections.sort(petsEncontradosList);

        for (String pet : petsEncontradosList) {
            System.out.println(pet
                    .replace("[", "")
                    .replace("]", ""));
        }

        return petsEncontradosList;
    }
}
