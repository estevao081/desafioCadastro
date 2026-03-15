package main.repositories;

import main.models.Pet;
import main.models.PetFiltro;
import main.services.pet.GerarNome;
import main.services.pet.PetUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PetFileRepository implements PetRepository {

    private final GerarNome gerarNome;
    private final PetUtils petUtils;
    private final String pathPets;

    public PetFileRepository(
            GerarNome gerarNome,
            PetUtils petUtils,
            String pathPets) {
        this.gerarNome = gerarNome;
        this.petUtils = petUtils;
        this.pathPets = pathPets;
    }

    @Override
    public void salvar(Pet pet) {

        String nomeArquivo = gerarNome.gerar(pet.getName());
        File arquivo = new File(pathPets, nomeArquivo);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for (String linha : pet.toLinhas()) {
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar pet", e);
        }
    }

    @Override
    public void alterar(String petSelecionado, int campo, String novoValor) {
        try (var paths = Files.walk(Paths.get(pathPets))) {
            paths
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
                        } catch (IOException e) {
                            System.err.println("ERRO: " + e.getMessage());
                        }
                    });

        } catch (IOException e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }

    @Override
    public void deletar(PetFiltro filtro) {
        try (var paths = Files.walk(Paths.get(pathPets))) {
            paths
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".TXT"))
                    .forEach(file -> {
                        try {
                            List<String> linhas = Files.readAllLines(file);
                            if (linhas.getFirst().equalsIgnoreCase(filtro.getNome())) {
                                Files.delete(file);
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

        try (var paths = Files.walk(Paths.get(pathPets))) {
            paths
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().toUpperCase().endsWith(".TXT"))
                    .forEach(file -> {
                        PetFiltro pet = petUtils.converterArquivoParaPet(file);
                        pets.add(pet);
                    });
        } catch (IOException e) {
            throw new RuntimeException("Erro ao listar pets", e);
        }
        return pets;
    }

    @Override
    public List<PetFiltro> buscar(PetFiltro filtro) {

        List<PetFiltro> pets = listarTodos();

        return pets.stream()
                .filter(p -> petUtils.campoBate(filtro.getNome(), p.getNome()))
                .filter(p -> petUtils.campoBate(filtro.getTipo(), p.getTipo()))
                .filter(p -> petUtils.campoBate(filtro.getGenero(), p.getGenero()))
                .filter(p -> petUtils.campoBate(filtro.getIdade(), p.getIdade()))
                .filter(p -> petUtils.campoBate(filtro.getPeso(), p.getPeso()))
                .filter(p -> petUtils.campoBate(filtro.getRaca(), p.getRaca()))
                .filter(p -> petUtils.campoBate(filtro.getEndereco(), p.getEndereco()))
                .filter(p -> petUtils.campoBate(filtro.getDataDeCadastro(), p.getDataDeCadastro()))
                .filter(p -> petUtils.campoBate(filtro.getAtributosExtras().toString(),
                        p.getAtributosExtras().toString()))
                .toList();
    }
}
