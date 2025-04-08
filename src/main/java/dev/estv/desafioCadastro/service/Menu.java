package dev.estv.desafioCadastro.service;

import dev.estv.desafioCadastro.controller.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu implements CommandLineRunner {

    @Autowired
    Controller controller;

    private final Scanner scan = new Scanner(System.in);

    public void run(String... args) {

        while (true) {
            try {
                System.out.println("1. Cadastrar um novo pet");
                System.out.println("2. Alterar os dados do pet cadastrado");
                System.out.println("3. Deletar um pet cadastrado");
                System.out.println("4. Listar todos os pets cadastrados");
                System.out.println("5. Listar pets por algum critério");
                System.out.println("6. Sair");

                String input = scan.nextLine();

                if (!input.matches("\\d+")) {
                    throw new IllegalArgumentException("ERRO: Opção inválida!\n");
                }

                if (Integer.parseInt(input) > 6 || Integer.parseInt(input) < 1) {
                    throw new IllegalArgumentException("ERRO: Opção inválida!\n");
                }

                int opcao = Integer.parseInt(input);

                switch (opcao) {
                    case 1:
                        controller.addPet();
                        break;
                    case 2:
                        controller.alterPet();
                        break;
                    case 3:
                        controller.deletePet();
                        break;
                    case 4:
                        controller.listPets();
                        break;
                    case 5:
                        controller.findPet();
                        break;
                    case 6:
                        System.out.println("Encerrando...");
                        System.exit(0);
                    default:
                        System.out.println("Digite uma opção válida.\n");
                        return;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}