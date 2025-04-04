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
                System.out.println("5. Listar pets por algum critério (idade, nome, raça)");
                System.out.println("6. Sair");

                String input = scan.nextLine();

                if (!input.matches("\\d+")) {
                    throw new IllegalArgumentException("Opção inválida!");
                }

                int opcao = Integer.parseInt(input);

                switch (opcao) {
                    case 1:
                        controller.addPet();
                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:
                        controller.searchPet();
                        break;
                    case 5:

                        break;
                    case 6:
                        System.out.println("Encerrando...");
                        System.exit(0);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}