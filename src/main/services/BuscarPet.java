package main.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BuscarPet {

    public static List<String> buscarPet(String path) {

        Scanner scanner = new Scanner(System.in);

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

        List<String> perguntasPet = List.of(
                "Informe o nome do pet:",
                "Informe o gênero do pet:",
                "Informe a idade do pet:",
                "Informe o peso do pet:",
                "Informe a raça do pet:",
                "Informe o endereço do pet:",
                "Informe a data de cadastro:"
        );

        Set<Integer> criterios = new HashSet<>();
        List<String> dadosDoPet = new ArrayList<>();

        String tipo;

        while (true) {
            System.out.println("Qual o tipo do animal? (Cão/Gato)");
            tipo = scanner.nextLine().trim().toUpperCase();

            if (tipo.equals("CÃO") || tipo.equals("CAO") || tipo.equals("CACHORRO")) {
                tipo = "CAO";
                break;
            }

            if (tipo.equals("GATO")) {
                break;
            }

            System.out.println("Tipo inválido. Informe Cão ou Gato.");
        }

        dadosDoPet.add(tipo);

        while (true) {
            try {
                opcoesMenu.forEach(System.out::println);
                String entrada = scanner.nextLine();

                if (entrada.isBlank()) break;

                int opcao = Integer.parseInt(entrada);

                if (opcao < 1 || opcao > 7) {
                    System.out.println("Informe uma opção válida.");
                    continue;
                }

                criterios.add(opcao - 1);

                System.out.println("Adicionar mais um critério? (S/N)");
                if (scanner.nextLine().equalsIgnoreCase("N")) break;

            } catch (NumberFormatException e) {
                System.out.println("Informe uma opção válida.");
            }
        }

        for (int c : criterios) {
            System.out.println(perguntasPet.get(c));
            String entrada = scanner.nextLine().trim().toUpperCase();

            if (entrada.isEmpty()) continue;

            if (c == 6) { // Data de cadastro
                entrada = normalizarDataCadastro(entrada);
            }
            dadosDoPet.add(entrada);
        }

        Set<String> petsEncontrados = new HashSet<>();
        AtomicInteger contador = new AtomicInteger(1);

        try {
            Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().toUpperCase().endsWith(".TXT"))
                    .forEach(file ->
                            processarArquivo(file, dadosDoPet, petsEncontrados, contador)
                    );
        } catch (IOException e) {
            System.err.println("ERRO: " + e.getMessage());
        }

        List<String> resultado = new ArrayList<>(petsEncontrados);
        Collections.sort(resultado);

        resultado.forEach(pet ->
                System.out.println(pet.replace("[", "").replace("]", ""))
        );
        return resultado;
    }

    private static void processarArquivo(
            Path file,
            List<String> criterios,
            Set<String> encontrados,
            AtomicInteger contador
    ) {

        try {
            List<String> conteudo = Files.readAllLines(file);
            String textoPet = conteudo.toString().toUpperCase();
            String nomeArquivo = file.getFileName().toString().toUpperCase();

            for (String criterio : criterios) {

                // Data de cadastro → nome do arquivo
                if (criterio.matches("\\d{8}")) {
                    if (!nomeArquivo.contains(criterio)) return;
                }
                // Demais critérios → conteúdo
                else {
                    if (!textoPet.contains(criterio)) return;
                }
            }

            encontrados.add(contador.getAndIncrement() + ". " + conteudo);

        } catch (IOException e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }

    private static String normalizarDataCadastro(String entrada) {

        entrada = entrada.trim();

        if (entrada.matches("\\d{1,2}[/-]\\d{1,2}[/-]\\d{2,4}")) {

            String[] partes = entrada.split("[/-]");

            String dia = partes[0].length() == 1 ? "0" + partes[0] : partes[0];
            String mes = partes[1].length() == 1 ? "0" + partes[1] : partes[1];

            String ano = partes[2];
            if (ano.length() == 2) {
                ano = "20" + ano;
            }

            return ano + mes + dia;
        }

        if (entrada.matches("\\d{8}.*")) {
            return entrada;
        }

        return entrada;
    }
}
