package main.services;

import java.util.Scanner;

public class Menu {

    public void exibirMenu() {

        final String path = "src/main/petsCadastrados";

        SalvarPet salvarPet = new SalvarPet();

        Scanner scan = new Scanner(System.in);

        boolean menuAtivo = true;

        while (menuAtivo) {

            System.out.println("1.Cadastrar um novo pet");
            System.out.println("2.Alterar os dados do pet cadastrado");
            System.out.println("3.Deletar um pet cadastrado");
            System.out.println("4.Listar todos os pets cadastrados");
            System.out.println("5.Listar pets por algum critério");
            System.out.println("6.Sair");

            String opcao = scan.nextLine();

            if (!opcao.matches("^-?\\d+$")) {
                System.out.println("ERRO: Digite um número válido");
            }

            switch (opcao) {

                case "1":
                    salvarPet.cadastrarPet(path);
                    break;

                case "2":
                    AtualizarPet.alterarPet(path);
                    break;

                case "3":
                    RemoverPet.deletarPet(path);
                    break;

                case "4":
                    ListarPets.listarTodosOsPets(path);
                    break;

                case "5":
                    BuscarPet.buscarPet(path);
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
