package main.services;

import main.models.Pet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PetService {

    public static void salvar(MontarPet montarPet, String pathFormulario, File diretorio) {

        //Construtor
        GerarNome gerarNome = new GerarNome();
        LerFormulario lerFormulario = new LerFormulario();

        Pet pet = montarPet.montar(lerFormulario, pathFormulario);

        String nomeArquivo = gerarNome.gerar(pet.getName());
        File arquivo = new File(diretorio, nomeArquivo);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for (String linha : pet.toLinhas()) {
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void atualizar(String pathPets) {

        Scanner scanner = new Scanner(System.in);
        GerarNome gerarNome = new GerarNome();

        //Utiliza o metodo buscarPet para encontrar o pet com as informações a serem atualizadas
        List<String> pets = buscar(pathPets);

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

    public static void listarTodos(String pathPets) {

        try {
            Files.walk(Paths.get(pathPets))
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

    public static void deletar(String pathPets) {

        Scanner scanner = new Scanner(System.in);

        //Busca por algum critério o pet a ser deletado
        List<String> pets = buscar(pathPets);

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
            Files.walk(Paths.get(pathPets))
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

    public static List<String> buscar(String pathPets) {

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
            Files.walk(Paths.get(pathPets))
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
