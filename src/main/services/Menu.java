package main.services;

import java.io.File;
import java.util.Scanner;

public class Menu {

    public static void exibirMenu() {

        //Paths
        final String pathFormulario = "src/main/forms/formulario.txt";
        final String pathPets = "petsCadastrados";

        //Define onde os pets cadastrados serão salvos
        File diretorio = new File(pathPets);

        //Cria o arquivo caso não exista (Evita o erro FileNotFound)
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

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
                    MontarPet montarPet = new MontarPet();
                    PetService.salvar(montarPet, pathFormulario, diretorio);
                    break;

                case "2":
                    PetService.atualizar(pathPets);
                    break;

                case "3":
                    PetService.deletar(pathPets);
                    break;

                case "4":
                    PetService.listarTodos(pathPets);
                    break;

                case "5":
                    PetService.buscar(pathPets);
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
