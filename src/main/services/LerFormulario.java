package main.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LerFormulario {

    public List<String> ler(String pathFormulario) {

        List<String> perguntas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(pathFormulario))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                perguntas.add(linha);
            }
        } catch (IOException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
        return perguntas;
    }
}
