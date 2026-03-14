package main.repositories;

import main.models.Pet;
import main.models.PetFiltro;

import java.util.List;

public interface PetRepository {

    void salvar(Pet pet);

    void alterar(String petSelecionado, int campo, String novoValor);

    void deletar(PetFiltro pet);

    List<PetFiltro> listarTodos();

    List<PetFiltro> buscar(PetFiltro filtro);
}

