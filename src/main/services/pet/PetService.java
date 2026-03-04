package main.services.pet;

import main.models.Campos;
import main.models.Pet;
import main.models.PetFiltro;
import main.repositories.PetRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public void salvar(MontarPet montarPet, RespostasUsuario respostasUsuario, LerFormulario lerFormulario,
                       String pathFormulario, Scanner scan) {
        Pet pet = montarPet.montar(respostasUsuario, lerFormulario, pathFormulario, scan);
        petRepository.salvar(pet);
    }

    public void atualizar(String pathPets, Scanner scan, GerarNome gerarNome, ValidarNumero validarNumero) {

        List<PetFiltro> petFiltroList = buscar(menuDeBuscaPetFiltro(scan));
        if (petFiltroList.isEmpty()) {
            System.out.println("Nenhum pet encontrado");
            return;
        }

        String petSelecionado = retornarPetParaAtualizar(scan, petFiltroList);
        int campo = retornarCampo(scan);
        String novoValor = retornarNovoValor(scan, campo);

        petRepository.alterar(pathPets, petSelecionado, campo, novoValor, scan, gerarNome, validarNumero);
    }

    public void deletar(String pathPets, Scanner scan) {
        PetFiltro filtro;
        List<PetFiltro> petFiltroList = buscar(menuDeBuscaPetFiltro(scan));
        int contador = 1;
        for (PetFiltro petFiltro : petFiltroList) {
            System.out.println(contador++ + " - " + petFiltro.toString());
        }
        System.out.println("Digite o índice do pet que deseja remover:");
        String identificador = scan.nextLine();
        filtro = petFiltroList.get(Integer.parseInt(identificador) - 1);
        petRepository.deletar(pathPets, filtro);
    }

    public void listarTodos() {
        List<PetFiltro> pets = petRepository.listarTodos();
        for (PetFiltro pet : pets) {
            System.out.println(pet.toString());
        }
    }

    public List<PetFiltro> buscar(PetFiltro filtro) {
        return petRepository.buscar(filtro);
    }

    public PetFiltro menuDeBuscaPetFiltro(Scanner scan) {

        PetFiltro filtro = new PetFiltro();

        System.out.println("Qual o tipo do pet?");
        filtro.setTipo(scan.nextLine().trim().toUpperCase());

        System.out.println("Como deseja buscar?");
        System.out.println(normalizarCampos());

        String campo = scan.nextLine().trim().toUpperCase();

        switch (campo) {
            case "NOME" -> {
                System.out.println("Informe o nome do pet:");
                filtro.setNome(scan.nextLine().toUpperCase());
            }
            case "GENERO" -> {
                System.out.println("Informe o gênero do pet:");
                filtro.setGenero(scan.nextLine().toUpperCase());
            }
            case "ENDERECO" -> {
                System.out.println("Informe o endereço do pet:");
                filtro.setEndereco(scan.nextLine().toUpperCase());
            }
            case "IDADE" -> {
                System.out.println("Informe a idade do pet:");
                filtro.setIdade(scan.nextLine());
            }
            case "PESO" -> {
                System.out.println("Informe o peso do pet:");
                filtro.setPeso(scan.nextLine());
            }
            case "RACA" -> {
                System.out.println("Informe a raça do pet:");
                filtro.setRaca(scan.nextLine().toUpperCase());
            }
            case "DATA DE CADASTRO" -> {
                System.out.println("Informe a data de cadastro do pet:");
                filtro.setDataDeCadastro(scan.nextLine());
            }
        }

        return filtro;
    }

    private static String retornarNovoValor(Scanner scan, int campo) {

        Pet pet = new Pet();
        String novoValor = "";

        switch (campo) {
            case 0 -> {
                System.out.println("Qual o novo nome do pet?");
                pet.setName(scan.nextLine());
                novoValor = pet.getName();
            }
            case 3 -> {
                System.out.println("Qual o novo endereço do pet?");
                pet.setAddress(scan.nextLine());
                novoValor = pet.getAddress();
            }
            case 4 -> {
                System.out.println("Qual a nova idade do pet?");
                pet.setAge(scan.nextLine());
                novoValor = pet.getAge();
            }
            case 5 -> {
                System.out.println("Qual o novo peso do pet?");
                pet.setWeight(scan.nextLine());
                novoValor = pet.getWeight();
            }
            case 6 -> {
                System.out.println("Qual o novo raça do pet?");
                pet.setRace(scan.nextLine());
                novoValor = pet.getRace();
            }
        }

        return novoValor;
    }

    private static int retornarCampo(Scanner scan) {

        int campo = 99;

        System.out.println("Qual campo deseja alterar?");
        System.out.println(normalizarCampos());
        String opcao = scan.nextLine().toLowerCase();

        switch (opcao) {
            case "nome" -> campo = 0;
            case "endereco" -> campo = 3;
            case "idade" -> campo = 4;
            case "peso" -> campo = 5;
            case "raca" -> campo = 6;
            default -> System.out.println("Informe um campo válido!");
        }

        return campo;
    }

    private static String retornarPetParaAtualizar(Scanner scan, List<PetFiltro> petFiltroList) {

        PetFiltro filtro;

        int contador = 1;
        for (PetFiltro petFiltro : petFiltroList) {
            System.out.println(contador++ + " - " + petFiltro.toString());
        }
        System.out.println("Digite o índice do pet que deseja atualizar:");
        String identificador = scan.nextLine();
        filtro = petFiltroList.get(Integer.parseInt(identificador) - 1);

        return filtro.getNome();
    }

    private static String normalizarCampos() {
        return Arrays.toString(Campos.values())
                .replace("[", "")
                .replace("]", "")
                .replace("_", " ");
    }

}
