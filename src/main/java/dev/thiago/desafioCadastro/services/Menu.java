package main.java.dev.thiago.desafioCadastro.services;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public void exibirMenu() {

        Scanner scan = new Scanner(System.in);

        LerFormulario read = new LerFormulario();

        boolean menuAtivo = true;

        while (menuAtivo) {

            System.out.println("1.Cadastrar um novo pet");
            System.out.println("2.Alterar os dados do pet cadastrado");
            System.out.println("3.Deletar um pet cadastrado");
            System.out.println("4.Listar todos os pets cadastrados");
            System.out.println("5.Listar pets por algum critério (idade, nome, raça)");
            System.out.println("6.Sair");

            String opcao = scan.nextLine();

            if (!opcao.matches("^-?\\d+$")) {
                System.out.println("ERRO: Digite um número válido");
            }

            switch (opcao) {

                case "1":
                    read.lerFormulario();
                    break;

                case "2":
                    System.out.println("Alterar");
                    break;

                case "3":
                    System.out.println("Deletar");
                    break;

                case "4":
                    System.out.println("Listar todos");
                    break;

                case "5":
                    System.out.println("Listar por critério");
                    break;

                case "6":
                    System.out.println("Encerrando...");
                    menuAtivo = false;
                    return;

                default:
                    System.out.println("ERRO: Informe uma opção válida");
                    break;
            }
        }
    }
}
