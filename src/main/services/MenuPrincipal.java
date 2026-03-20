package main.services;

import main.services.form.MenuForm;
import main.services.pet.MenuPet;

import java.util.Scanner;

public class MenuPrincipal {

    private final Scanner scan;
    private final MenuPet menuPet;
    private final MenuForm menuForm;

    public MenuPrincipal(Scanner scan,
                         MenuPet menuPet,
                         MenuForm menuForm) {

        this.scan = scan;
        this.menuPet = menuPet;
        this.menuForm = menuForm;
    }

    public void exibir() {

        while (true) {

            System.out.println("1 - Iniciar o sistema para cadastro de PETS");
            System.out.println("2 - Iniciar o sistema para alterar formulário");
            System.out.println("3 - Encerrar");

            String opcao = scan.nextLine();

            switch (opcao) {
                case "1" -> menuPet.exibir();

                case "2" -> menuForm.exibirMenuForm(scan);

                case "3" -> {
                    System.out.println("Encerrando...");
                    return;
                }

                default -> System.out.println("Opção inválida");
            }
        }
    }
}