package main.java.dev.thiago.desafioCadastro.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LerFormulario {

    public void lerFormulario() {

        String path = "src/main/java/dev/thiago/desafioCadastro/models/formulario.txt";

        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
