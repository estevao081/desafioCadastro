package main.services.pet;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class MenuPet {

    public static void exibirMenuPet(
            String pathFormulario,
            String pathPets,
            File diretorio,
            Scanner scan,
            MontarPet montarPet,
            GerarNome gerarNome,
            EscreverArquivo escreverArquivo,
            RespostasUsuario respostasUsuario,
            LerFormulario lerFormulario,
            AlterarArquivo alterarArquivo,
            ValidarNumero validarNumero) {

        while (true) {

            List<String> perguntasPet = List.of(
                    "1.Cadastrar um novo pet",
                    "2.Alterar os dados do pet cadastrado",
                    "3.Deletar um pet cadastrado",
                    "4.Listar todos os pets cadastrados",
                    "5.Listar pets por algum critério",
                    "6.Sair"
            );

            perguntasPet.forEach(System.out::println);

            String opcaoPet = scan.nextLine();

            switch (opcaoPet) {
                case "1" -> PetService.salvar(montarPet, gerarNome, diretorio, escreverArquivo,
                        respostasUsuario, pathFormulario, lerFormulario, scan);

                case "2" -> PetService.atualizar(pathPets, scan, gerarNome, alterarArquivo, validarNumero);

                case "3" -> PetService.deletar(pathPets);

                case "4" -> PetService.listarTodos(pathPets);

                case "5" -> PetService.buscar(pathPets);

                case "6" -> {
                    System.out.println("Encerrando...");
                    return;
                }

                default -> System.out.println("ERRO: Informe uma opção válida");
            }
        }
    }
}
