package main.services.pet;

public class ValidarNumero {

    public Integer validarOpcaoPet(String input, int tamanhoLista) {

        int indice = Integer.parseInt(input) - 1;

        if (indice < 0  || indice > tamanhoLista) {
            System.out.println("ERRO: Informe uma opção válida");
        }

        return indice;
    }

    public Integer validarCampoPet(String input) {

        int indice = Integer.parseInt(input) - 1;

        if (indice >= 2) {
            indice = indice + 2;
        }

        if (indice < 0) {
            System.out.println("ERRO: Informe uma opção válida");
        }

        return indice;
    }
}
