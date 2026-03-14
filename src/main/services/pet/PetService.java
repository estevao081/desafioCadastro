package main.services.pet;

import main.models.Pet;
import main.models.PetFiltro;
import main.repositories.PetRepository;

import java.util.List;
import java.util.Scanner;

public class PetService {

    private final PetRepository petRepository;
    private final MontarPet montarPet;
    private final RespostasUsuario respostasUsuario;
    private final LerFormulario lerFormulario;
    private final String pathFormulario;
    private final Scanner scan;
    private final PetUtils petUtils;

    public PetService(
            PetRepository petRepository,
            MontarPet montarPet,
            RespostasUsuario respostasUsuario,
            LerFormulario lerFormulario,
            String pathFormulario,
            Scanner scan,
            PetUtils petUtils) {
        this.petRepository = petRepository;
        this.montarPet = montarPet;
        this.respostasUsuario = respostasUsuario;
        this.lerFormulario = lerFormulario;
        this.pathFormulario = pathFormulario;
        this.scan = scan;
        this.petUtils = petUtils;
    }

    public void salvar() {
        Pet pet = montarPet.montar(respostasUsuario, lerFormulario, pathFormulario, scan);
        petRepository.salvar(pet);
        System.out.println("Pet salvo com sucesso!");
    }

    public void atualizar() {

        List<PetFiltro> petFiltroList = buscar();
        if (petFiltroList.isEmpty()) {
            System.out.println("Nenhum pet encontrado");
            return;
        }

        String petSelecionado = petUtils.retornarPetParaAtualizar(scan, petFiltroList);
        int campo = petUtils.retornarCampo(scan);
        String novoValor = petUtils.retornarNovoValor(scan, campo);

        petRepository.alterar(petSelecionado, campo, novoValor);
        System.out.println("Pet alterado com sucesso!");
    }

    public void deletar() {

        PetFiltro filtro;
        List<PetFiltro> petFiltroList = buscar();

        if (petFiltroList.isEmpty()) {
            System.out.println("Nenhum pet encontrado");
        }

        int contador = 1;

        for (PetFiltro petFiltro : petFiltroList) {
            System.out.println(contador++ + " - " + petFiltro.toString());
        }

        System.out.println("Digite o índice do pet que deseja remover:");
        String identificador = scan.nextLine();

        filtro = petFiltroList.get(Integer.parseInt(identificador) - 1);

        petRepository.deletar(filtro);
        System.out.println("Pet deletado com sucesso!");
    }

    public void listarTodos() {
        List<PetFiltro> pets = petRepository.listarTodos();
        for (PetFiltro pet : pets) {
            System.out.println(pet.toString());
        }
    }

    public List<PetFiltro> buscar() {
        List<PetFiltro> pets = petRepository.buscar(petUtils.menuDeBuscaPetFiltro(scan));
        if (pets.isEmpty()) {
            System.out.println("Nenhum pet encontrado!");
        }
        return pets;
    }
}
