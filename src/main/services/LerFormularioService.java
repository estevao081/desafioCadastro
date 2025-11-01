package main.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LerFormularioService {

    public List<String> respostas;

    public List<String> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<String> respostas) {
        this.respostas = respostas;
    }

    public void lerFormulario() {

        String path = "src/main/forms/formulario.txt";

        Scanner scan = new Scanner(System.in);

        List<String> r = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
                r.add(scan.nextLine().toUpperCase());
            }
        } catch (IOException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
        setRespostas(r);
    }
}
