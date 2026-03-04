package main.repositories;

import main.models.Pet;
import main.models.PetFiltro;
import main.services.pet.GerarNome;
import main.services.pet.ValidarNumero;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetFileRepository implements PetRepository {

    private final File diretorio;
    private final GerarNome gerarNome;

    public PetFileRepository(File diretorio,
                             GerarNome gerarNome) {
        this.diretorio = diretorio;
        this.gerarNome = gerarNome;
    }

    @Override
    public void salvar(Pet pet) {
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

    @Override
    public void alterar(String pathPets, String petSelecionado, int campo, String novoValor, Scanner scan,
                        GerarNome gerarNome, ValidarNumero validarNumero) {
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

    @Override
    public void deletar(String pathPets, PetFiltro filtro) {
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

    @Override
    public List<PetFiltro> listarTodos() {

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

    @Override
    public List<PetFiltro> buscar(PetFiltro filtro) {

        return listarTodos().stream()
                .filter(p -> campoBate(filtro.getNome(), p.getNome()))
                .filter(p -> campoBate(filtro.getTipo(), p.getTipo()))
                .filter(p -> campoBate(filtro.getGenero(), p.getGenero()))
                .filter(p -> campoBate(filtro.getIdade(), p.getIdade()))
                .filter(p -> campoBate(filtro.getPeso(), p.getPeso()))
                .filter(p -> campoBate(filtro.getRaca(), p.getRaca()))
                .filter(p -> campoBate(filtro.getEndereco(), p.getEndereco()))
                .filter(p -> campoBate(filtro.getDataDeCadastro(), p.getDataDeCadastro()))
                .toList();
    }

    private boolean campoBate(String filtro, String valor) {
        return filtro == null || filtro.isBlank() ||
                valor.contains(filtro);
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

