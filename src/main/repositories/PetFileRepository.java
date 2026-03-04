package main.repositories;

import main.models.Pet;
import main.models.PetFiltro;
import main.services.pet.GerarNome;
import main.services.pet.PercorrerArquivos;
import main.services.pet.ValidarNumero;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class PetFileRepository implements PetRepository {

    private final File diretorio;
    private final GerarNome gerarNome;
    private final PercorrerArquivos percorrerArquivos;

    public PetFileRepository(File diretorio,
                             GerarNome gerarNome,
                             PercorrerArquivos percorrerArquivos) {
        this.diretorio = diretorio;
        this.gerarNome = gerarNome;
        this.percorrerArquivos = percorrerArquivos;
    }

    @Override
    public void salvar(Pet pet) {

        String nomeArquivo = gerarNome.gerar(pet.getName());
        File arquivo = new File(diretorio, nomeArquivo);

        percorrerArquivos.escrever(arquivo, pet);
    }

    @Override
    public List<PetFiltro> listarTodos() {
        return percorrerArquivos.listarTodos(diretorio);
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

    @Override
    public void deletar(String pathPets, PetFiltro pet) {
        percorrerArquivos.remover(pathPets, pet);
    }

    @Override
    public void alterar(String pathPets, String petSelecionado, int campo, String novoValor, Scanner scan,
                        GerarNome gerarNome, PercorrerArquivos percorrerArquivos, ValidarNumero validarNumero) {
        percorrerArquivos.alterar(pathPets, petSelecionado, campo, novoValor, gerarNome);
    }


}

