package main.services.pet;

import main.models.PetFiltro;

import java.util.List;
import java.util.Scanner;

public class MenuPet {

    private final Scanner scan;
    private final PetService petService;

    public MenuPet(
            Scanner scan,
            PetService petService) {
        this.scan = scan;
        this.petService = petService;
    }

    public void exibir() {

        while (true) {

            System.out.println("1.Cadastrar um novo pet");
            System.out.println("2.Alterar os dados do pet cadastrado");
            System.out.println("3.Deletar um pet cadastrado");
            System.out.println("4.Listar todos os pets cadastrados");
            System.out.println("5.Listar pets por algum critério");
            System.out.println("6.Sair");

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
        petService.salvar();
    }

    private void atualizar() {
        petService.atualizar();
    }

    private void deletar() {
        petService.deletar();
    }

    private void listar() {
        petService.listarTodos();
    }

    private void buscar() {
        List<PetFiltro> petFiltroList = petService.buscar();
        petFiltroList.forEach(System.out::println);
    }
}
