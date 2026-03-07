package main.services.pet;

import main.models.PetFiltro;

import java.util.List;
import java.util.Scanner;

public class MenuPet {

    private final String pathFormulario;
    private final String pathPets;
    private final Scanner scan;
    private final MontarPet montarPet;
    private final GerarNome gerarNome;
    private final RespostasUsuario respostasUsuario;
    private final LerFormulario lerFormulario;
    private final ValidarNumero validarNumero;
    private final PetService petService;

    public MenuPet(String pathFormulario,
                   String pathPets,
                   Scanner scan,
                   MontarPet montarPet,
                   GerarNome gerarNome,
                   RespostasUsuario respostasUsuario,
                   LerFormulario lerFormulario,
                   ValidarNumero validarNumero,
                   PetService petService) {

        this.pathFormulario = pathFormulario;
        this.pathPets = pathPets;
        this.scan = scan;
        this.montarPet = montarPet;
        this.gerarNome = gerarNome;
        this.respostasUsuario = respostasUsuario;
        this.lerFormulario = lerFormulario;
        this.validarNumero = validarNumero;
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

                case "4" -> petService.listarTodos();

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
        petService.salvar(montarPet, respostasUsuario, lerFormulario, pathFormulario, scan);
    }

    private void atualizar() {
        petService.atualizar(pathPets, scan, gerarNome, validarNumero);
    }

    private void deletar() {
        petService.deletar(pathPets, scan);
    }

    private void buscar() {
        List<PetFiltro> petFiltroList = petService.buscar(petService.menuDeBuscaPetFiltro(scan));
        petFiltroList.forEach(System.out::println);
    }
}
