package main.services.pet;

import main.models.Campos;
import main.models.Pet;
import main.models.PetFiltro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PetUtils {

    public boolean campoBate(String filtro, String valor) {
        return filtro == null || filtro.isBlank() ||
                valor.contains(filtro);
    }

    public PetFiltro converterArquivoParaPet(Path file) {

        final int camposFixos = 7;

        try {
            List<String> linhas = Files.readAllLines(file)
                    .stream()
                    .map(String::trim)
                    .toList();

            if (linhas.size() < camposFixos) {
                throw new RuntimeException("Arquivo inválido: " + file);
            }

            PetFiltro pet = new PetFiltro();

            pet.setNome(linhas.get(0));
            pet.setTipo(linhas.get(1));
            pet.setGenero(linhas.get(2));
            pet.setEndereco(linhas.get(3));
            pet.setIdade(String.valueOf(linhas.get(4)));
            pet.setPeso(String.valueOf(linhas.get(5)));
            pet.setRaca(linhas.get(6));
            pet.setDataDeCadastro(formatarData(file.getFileName().toString().substring(0, 8)));

            for (int i = camposFixos; i < linhas.size(); i++) {
                pet.addAtributoExtra(linhas.get(i));
            }

            return pet;

        } catch (IOException e) {
            throw new RuntimeException("Erro ao converter arquivo: " + file, e);
        }
    }

    public String formatarData(String data) {
        return LocalDate.parse(data, DateTimeFormatter.ofPattern("yyyyMMdd"))
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public PetFiltro menuDeBuscaPetFiltro(Scanner scan) {

        PetFiltro filtro = new PetFiltro();

        System.out.println("Qual o tipo do pet?");
        filtro.setTipo(scan.nextLine().trim().toUpperCase());

        System.out.println("Como deseja buscar?");
        System.out.println(normalizarCampos());

        String campo = scan.nextLine().trim().toUpperCase();

        switch (campo) {
            case "NOME" -> {
                System.out.println("Informe o nome do pet:");
                filtro.setNome(scan.nextLine().toUpperCase());
            }
            case "GENERO" -> {
                System.out.println("Informe o gênero do pet:");
                filtro.setGenero(scan.nextLine().toUpperCase());
            }
            case "ENDERECO" -> {
                System.out.println("Informe o endereço do pet:");
                filtro.setEndereco(scan.nextLine().toUpperCase());
            }
            case "IDADE" -> {
                System.out.println("Informe a idade do pet:");
                filtro.setIdade(scan.nextLine());
            }
            case "PESO" -> {
                System.out.println("Informe o peso do pet:");
                filtro.setPeso(scan.nextLine());
            }
            case "RACA" -> {
                System.out.println("Informe a raça do pet:");
                filtro.setRaca(scan.nextLine().toUpperCase());
            }
            case "DATA DE CADASTRO" -> {
                System.out.println("Informe a data de cadastro do pet:");
                filtro.setDataDeCadastro(scan.nextLine());
            }
            default -> System.out.println("Informe uma opção válida");
        }

        return filtro;
    }

    public String retornarNovoValor(Scanner scan, int campo) {

        Pet pet = new Pet();
        String novoValor = "";

        switch (campo) {
            case 0 -> {
                System.out.println("Qual o novo nome do pet?");
                pet.setName(scan.nextLine());
                novoValor = pet.getName();
            }
            case 3 -> {
                System.out.println("Qual o novo endereço do pet?");
                pet.setAddress(scan.nextLine());
                novoValor = pet.getAddress();
            }
            case 4 -> {
                System.out.println("Qual a nova idade do pet?");
                pet.setAge(scan.nextLine());
                novoValor = pet.getAge();
            }
            case 5 -> {
                System.out.println("Qual o novo peso do pet?");
                pet.setWeight(scan.nextLine());
                novoValor = pet.getWeight();
            }
            case 6 -> {
                System.out.println("Qual o novo raça do pet?");
                pet.setRace(scan.nextLine());
                novoValor = pet.getRace();
            }
        }

        return novoValor;
    }

    public int retornarCampo(Scanner scan) {

        int campo = 99;

        System.out.println("Qual campo deseja alterar?");
        System.out.println(normalizarCampos());
        String opcao = scan.nextLine().toLowerCase();


        switch (opcao) {
            case "nome" -> campo = 0;
            case "endereco" -> campo = 3;
            case "idade" -> campo = 4;
            case "peso" -> campo = 5;
            case "raca" -> campo = 6;
            default -> System.out.println("Informe um campo válido!");
        }

        return campo;
    }

    public String retornarPetParaAtualizar(Scanner scan, List<PetFiltro> petFiltroList) {

        int contador = 1;
        for (PetFiltro petFiltro : petFiltroList) {
            System.out.println(contador++ + " - " + petFiltro);
        }

        while (true) {

            System.out.println("Digite o índice do pet que deseja atualizar:");
            String identificador = scan.nextLine();

            try {

                int indice = Integer.parseInt(identificador);

                if (indice < 1 || indice > petFiltroList.size()) {
                    System.out.println("ERRO: Índice inválido.");
                    continue;
                }

                PetFiltro filtro = petFiltroList.get(indice - 1);
                return filtro.getNome();

            } catch (NumberFormatException e) {
                System.out.println("ERRO: Informe um número válido.");
            }
        }
    }

    public String normalizarCampos() {
        return Arrays.toString(Campos.values())
                .replace("[", "")
                .replace("]", "")
                .replace("_", " ");
    }
}
