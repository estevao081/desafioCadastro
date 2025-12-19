package main.services;

import main.exceptions.*;
import main.models.PetModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class PetService {

    String path = "src/main/petsCadastrados";
    File diretorio = new File(path);

    public void salvarPet() {


        LerFormularioService read = new LerFormularioService();

        read.lerFormulario();

        List<String> respostas = read.getRespostas();

        PetModel pet = new PetModel();

        GerarNomeService gerarNomeService = new GerarNomeService();

        final String NA = "NÃO INFORMADO";
        String name = respostas.getFirst().trim();
        String type = respostas.get(1);
        String gender = respostas.get(2);
        String address = respostas.get(3);
        String age = respostas.get(4);
        String weight = respostas.get(5);
        String race = respostas.get(6);

        if (!name.matches("^$|^[A-Za-zÀ-ÿ]+ [A-Za-zÀ-ÿ]+$")) {
            throw new InvalidNameException();
        }

        if (type.equalsIgnoreCase("gato")) {
            pet.setType(PetModel.PetType.GATO);
        }

        if (type.equalsIgnoreCase("cao")
                || type.equalsIgnoreCase("cão")
                || type.equalsIgnoreCase("cachorro")) {
            pet.setType(PetModel.PetType.CAO);
        }

        if (gender.equalsIgnoreCase("f")) {
            pet.setGender(PetModel.PetGender.F);
        }

        if (gender.equalsIgnoreCase("m")) {
            pet.setGender(PetModel.PetGender.M);
        }

        if (!address.matches("^$|^[A-Za-zÀ-ÿ0-9\\s\\.]+,\\s*\\d+[A-Za-z0-9\\s\\-]*,\\s*[A-Za-zÀ-ÿ\\s]+$")) {
            throw new InvalidAddressException();
        }

        if (respostas.get(4).contains(",")) {
            age = respostas.get(4).replace(",", ".");
        }

        if (respostas.get(5).contains(",")) {
            weight = respostas.get(5).replace(",", ".");
        }

        if (!age.isEmpty()) {
            if (Double.parseDouble(age) > 20 || Double.parseDouble(age) < 0.1) {
                throw new InvalidAgeException();
            }
        }

        if (!weight.isEmpty()) {
            if (Double.parseDouble(weight) > 60 || Double.parseDouble(weight) < 0.5) {
                throw new InvalidWeightException();
            }
        }

        if (!race.matches("^$|^[A-Za-zÀ-ÿ\\s'-]+$")) {
            throw new InvalidRaceException();
        }

        if (name.isEmpty()) {
            name = NA;
        }

        if (race.isEmpty()) {
            race = NA;
        }

        if (address.isEmpty()) {
            address = NA;
        }

        if (weight.isEmpty()) {
            weight = NA;
        }

        if (age.isEmpty()) {
            age = NA;
        }

        pet.setName(name);
        pet.setAddress(address);
        pet.setAge(age);
        pet.setWeight(weight);
        pet.setRace(race);


        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        String nomeArquivo = gerarNomeService.gerarNome(name + ".txt");

        File arquivo = new File(diretorio, nomeArquivo);

        List<String> respostasParaSalvar = new ArrayList<>(List.of());
        respostasParaSalvar.add(pet.getName());
        respostasParaSalvar.add(pet.getType().toString());
        respostasParaSalvar.add(pet.getGender().toString());
        respostasParaSalvar.add(pet.getAge());
        respostasParaSalvar.add(pet.getWeight());
        respostasParaSalvar.add(pet.getAddress());
        respostasParaSalvar.add(pet.getRace());

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {

            for (int i = 0; i < respostasParaSalvar.size(); i++) {
                bw.write(respostasParaSalvar.get(i));
                if (i < respostasParaSalvar.size() - 1) {
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> buscarPet() {

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

        System.out.println("Qual o tipo do animal? (Cão/Gato)");
        dadosDoPet.add(scanner.nextLine().toUpperCase());
        if (dadosDoPet.getFirst().equalsIgnoreCase("cão")
                || dadosDoPet.getFirst().equalsIgnoreCase("cao")
                || dadosDoPet.getFirst().equalsIgnoreCase("cachorro")) {
            dadosDoPet.set(0, "CAO");
        }

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

        Set<String> petsEncontrados = new HashSet<>();
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

        List<String> petsEncontradosList = new ArrayList<>(petsEncontrados);

        Collections.sort(petsEncontradosList);

        for (String pet : petsEncontradosList) {
            if (pet.contains(dadosDoPet.toString())) {
            }
            System.out.println(pet
                    .replace("[", "")
                    .replace("]", ""));
        }

        return petsEncontradosList;
    }

    public void listarTodosOsPets() {

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

    public void alterarPet() {

        Scanner scanner = new Scanner(System.in);

        List<String> pets = buscarPet();

        if (pets.isEmpty()) {
            System.out.println("Nenhum pet encontrado.");
            return;
        }

        System.out.println("Informe o número do pet que deseja alterar:");
        int opcaoPet = Integer.parseInt(scanner.nextLine()) - 1;

        if (opcaoPet < 0 || opcaoPet >= pets.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        String petSelecionado = pets.get(opcaoPet);

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
                    .forEach(file -> {
                        try {

                            List<String> linhas = Files.readAllLines(file);

                            if (linhas.toString().equalsIgnoreCase(
                                    petSelecionado.replaceFirst("^\\d+\\. ", "")
                            )) {
                                linhas.set(campo, novoValor);
                                Files.write(file, linhas);
                                System.out.println("Pet alterado com sucesso!");
                            }

                        } catch (IOException e) {
                            System.err.println("ERRO: " + e.getMessage());
                        }
                    });
        } catch (IOException e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }

    public void deletarPet() {

        Scanner scanner = new Scanner(System.in);

        List<String> pets = buscarPet();

        if (pets.isEmpty()) {
            System.out.println("Nenhum pet encontrado.");
            return;
        }

        int opcaoPet;

        while (true) {
            try {
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
