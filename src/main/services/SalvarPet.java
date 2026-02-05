package main.services;

import main.models.PetModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SalvarPet {

    public void cadastrarPet(String path) {

        //Define onde os pets cadastrados serão salvos
        File diretorio = new File(path);

        //Cria o arquivo caso não exista (Evita o erro FileNotFound)
        if (!diretorio.exists()) {
            diretorio.mkdir();
        }

        //Construtores
        LerFormulario lerFormulario = new LerFormulario();
        GerarNome gerarNome = new GerarNome();

        //Lê as perguntas do formulário
        lerFormulario.ler();

        //Salva em uma lista as respostas do usuário
        List<String> respostas = lerFormulario.getRespostas();

        PetModel pet = new PetModel();
        pet.setName(respostas.getFirst());
        pet.setType(PetModel.PetType.valueOf(respostas.get(1)));
        pet.setGender(PetModel.PetGender.valueOf(respostas.get(2)));
        pet.setAddress(respostas.get(3));
        pet.setAge(respostas.get(4));
        pet.setWeight(respostas.get(5));
        pet.setRace(respostas.get(6));

        String nomeArquivo = gerarNome.gerarNome(pet.getName() + ".txt");
        File arquivo = new File(diretorio, nomeArquivo);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for(String linha : pet.toLinhas()) {
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
