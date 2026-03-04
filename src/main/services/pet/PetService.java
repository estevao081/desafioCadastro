package main.services.pet;

import main.models.Pet;
import main.models.PetFiltro;
import main.repositories.PetRepository;

import java.util.List;
import java.util.Scanner;

public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public void salvar(Pet pet) {
        petRepository.salvar(pet);
    }

    public void listarTodos() {
        List<PetFiltro> pets = petRepository.listarTodos();
        for (PetFiltro pet : pets) {
            System.out.println(pet.toString());
        }
    }

    public List<PetFiltro> buscar(PetFiltro filtro) {
        return petRepository.buscar(filtro);
    }

    public void deletar(String pathPets, PetFiltro pet) {
        petRepository.deletar(pathPets, pet);
    }

    public void atualizar(String pathPets, String petSelecionado, int campo, String novoValor, Scanner scan,
                          GerarNome gerarNome, PercorrerArquivos percorrerArquivos, ValidarNumero validarNumero) {
        petRepository.alterar(pathPets, petSelecionado, campo, novoValor, scan, gerarNome, percorrerArquivos,
                validarNumero);
    }

}
