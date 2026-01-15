package main.services;

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
        if(!diretorio.exists()) {
            diretorio.mkdir();
        }

        //Construtores
        ValidarPet validarPet = new ValidarPet();
        LerFormulario lerFormulario = new LerFormulario();
        GerarNome gerarNome = new GerarNome();

        //Lê as perguntas do formulário
        lerFormulario.ler();

        //Salva em uma lista as respostas do usuário
        List<String> respostas = lerFormulario.getRespostas();

        String name = respostas.getFirst().trim();
        String type = respostas.get(1);
        String gender = respostas.get(2);
        String address = respostas.get(3);
        String age = respostas.get(4);
        String weight = respostas.get(5);
        String race = respostas.get(6);

        //Valida os dados do Pet antes de salvar
        List<String> respostasParaSalvar = new ArrayList<>();
        respostasParaSalvar.add(validarPet.validarNome(name));
        respostasParaSalvar.add(String.valueOf(validarPet.validarTipo(type)));
        respostasParaSalvar.add(String.valueOf(validarPet.validarGenero(gender)));
        respostasParaSalvar.add(validarPet.validarEndereco(address));
        respostasParaSalvar.add(validarPet.validarIdade(age));
        respostasParaSalvar.add(validarPet.validarPeso(weight));
        respostasParaSalvar.add(validarPet.validarRaca(race));

        String nomeArquivo = gerarNome.gerarNome(name + ".txt");
        File arquivo = new File(diretorio, nomeArquivo);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            //Utilizando for tradicional para que o texto quebre linha enquanto houver resposta a ser adicionada
            for (int i = 0; i < respostasParaSalvar.size(); i++) {
                bw.write(respostasParaSalvar.get(i));
                if (i < respostasParaSalvar.size() - 1) {
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
