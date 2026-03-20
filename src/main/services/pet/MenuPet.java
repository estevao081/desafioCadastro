package main.services.pet;

import main.models.Pet;
import main.models.PetFiltro;

import java.util.List;
import java.util.Scanner;

public class MenuPet {

    private final Scanner scan;
    private final PetService petService;
    private final MontarPet montarPet;
    private final PetUtils petUtils;

    public MenuPet(
            Scanner scan,
            PetService petService,
            MontarPet montarPet,
            PetUtils petUtils
    ) {
        this.scan = scan;
        this.petService = petService;
        this.montarPet = montarPet;
        this.petUtils = petUtils;
    }

    public void exibir() {

        while (true) {

            List<String> opcoes = List.of(
                    "1.Cadastrar um novo pet",
                    "2.Alterar os dados do pet cadastrado",
                    "3.Deletar um pet cadastrado",
                    "4.Listar todos os pets cadastrados",
                    "5.Listar pets por algum critério",
                    "6.Sair"
            );

            opcoes.forEach(System.out::println);

            String opcaoPet = scan.nextLine();

            switch (opcaoPet) {

                case "1" -> cadastrar();

                case "2" -> atualizar();

                case "3" -> deletar();

                case "4" -> listar();

                case "5" -> buscar();

                case "6" -> {
                    System.out.println("Encerrando...");
                    return;
                }

                default -> System.out.println("ERRO: Informe uma opção válida");
            }
        }
    }

    private void cadastrar() {

        Pet pet = montarPet.montar(scan);

        petService.salvar(pet);
        System.out.println("Pet salvo com sucesso!");
    }

    private void atualizar() {

        List<PetFiltro> petFiltroList = petService.buscar(petUtils.menuDeBuscaPetFiltro(scan));

        if (petFiltroList.isEmpty()) {
            System.out.println("Nenhum pet encontrado!");
            return;
        }

        PetFiltro petSelecionado = petUtils.retornarPetComIndiceValidado(scan, petFiltroList);

        int campo = petUtils.retornarCampo(scan);

        String novoValor = petUtils.retornarNovoValor(scan, campo);

        petService.atualizar(petSelecionado.getNome(), campo, novoValor);
        System.out.println("Pet atualizado com sucesso!");
    }

    private void deletar() {

        List<PetFiltro> petFiltroList = petService.buscar(petUtils.menuDeBuscaPetFiltro(scan));

        if (petFiltroList.isEmpty()) {
            System.out.println("Nenhum pet encontrado!");
            return;
        }

        petService.deletar(petUtils.retornarPetComIndiceValidado(scan, petFiltroList));
        System.out.println("Pet deletado com sucesso!");
    }

    private void listar() {

        List<PetFiltro> pets = petService.listarTodos();

        if (pets.isEmpty()) {
            System.out.println("Nenhum pet encontrado!");
            return;
        }

        int i = 1;

        System.out.println("Pets encontrados:");
        for (PetFiltro pet : pets) {
            System.out.println(i++ + ". " + pet);
        }
    }

    private void buscar() {

        List<PetFiltro> pets = petService.buscar(petUtils.menuDeBuscaPetFiltro(scan));

        if (pets.isEmpty()) {
            System.out.println("Nenhum pet encontrado!");
            return;
        }

        int i = 1;

        System.out.println("Pets encontrados:");
        for (PetFiltro pet : pets) {
            System.out.println(i++ + ". " + pet);
        }
    }
}
