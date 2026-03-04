package main.services.pet;

import main.models.Pet;
import main.models.PetFiltro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PercorrerArquivos {

    public void escrever(File arquivo, Pet pet) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for (String linha : pet.toLinhas()) {
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void alterar(String pathPets, String petSelecionado, int campo, String novoValor, GerarNome gerarNome) {

        try {
            Files.walk(Paths.get(pathPets))
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".TXT"))
                    .forEach(filePath -> {
                        try {

                            List<String> linhas = Files.readAllLines(filePath);

                            if (!linhas.getFirst().equalsIgnoreCase(petSelecionado)) return;

                            linhas.set(campo, novoValor);
                            Files.write(filePath, linhas);

                            //Atualiza o nome do arquivo caso o nome do pet seja alterado
                            if (campo == 0) {
                                String novoNomeArquivo = gerarNome.gerar(novoValor);
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

    public void remover(String pathPets, PetFiltro filtro) {

        try {
            Files.walk(Paths.get(pathPets))
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".TXT"))
                    .forEach(file -> {
                        try {
                            List<String> linhas = Files.readAllLines(file);
                            if (linhas.contains(filtro.getNome())) {
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

    public List<PetFiltro> listarTodos(File diretorio) {

        List<PetFiltro> pets = new ArrayList<>();

        try {
            Files.walk(diretorio.toPath())
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().toUpperCase().endsWith(".TXT"))
                    .forEach(file -> {
                        PetFiltro pet = converterArquivoParaPet(file);
                        pets.add(pet);
                    });

        } catch (IOException e) {
            throw new RuntimeException("Erro ao listar pets", e);
        }

        return pets;
    }

    private PetFiltro converterArquivoParaPet(Path file) {

        try {
            List<String> linhas = Files.readAllLines(file)
                    .stream()
                    .map(String::trim)
                    .toList();

            if (linhas.size() < 7) {
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
            pet.setDataDeCadastro(file.getFileName().toString().substring(0, 8));

            return pet;

        } catch (IOException e) {
            throw new RuntimeException("Erro ao converter arquivo: " + file, e);
        }
    }
}
