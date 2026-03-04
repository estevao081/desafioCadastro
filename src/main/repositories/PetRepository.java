package main.repositories;

import main.models.Pet;
import main.models.PetFiltro;
import main.services.pet.GerarNome;
import main.services.pet.PercorrerArquivos;
import main.services.pet.ValidarNumero;

import java.util.List;
import java.util.Scanner;

public interface PetRepository {

    void salvar(Pet pet);

    List<PetFiltro> listarTodos();

    List<PetFiltro> buscar(PetFiltro filtro);

    void deletar(String pathPets, PetFiltro pet);

    void alterar(String pathPets, String petSelecionado, int campo, String novoValor, Scanner scan,
                 GerarNome gerarNome, PercorrerArquivos percorrerArquivos, ValidarNumero validarNumero);
}

