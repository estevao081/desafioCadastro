package main.services.pet;

import main.models.Pet;
import main.models.PetFiltro;
import main.repositories.PetRepository;

import java.util.List;

public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public void salvar(Pet pet) {
        petRepository.salvar(pet);
    }

    public void atualizar(String petSelecionado, int campo, String novoValor) {
        petRepository.alterar(petSelecionado, campo, novoValor);
    }

    public void deletar(PetFiltro filtro) {
        petRepository.deletar(filtro);
    }

    public List<PetFiltro> listarTodos() {
        return petRepository.listarTodos();
    }

    public List<PetFiltro> buscar(PetFiltro filtro) {
        return petRepository.buscar(filtro);
    }
}
