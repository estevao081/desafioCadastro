package main;

import main.services.MenuPrincipal;
import main.services.form.AtualizarForm;
import main.services.pet.*;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Paths
        final String pathFormulario = "src/main/forms/FORMULARIO.TXT";
        final String pathPets = "petsCadastrados";

        //Define onde os pets cadastrados serão salvos
        File diretorio = new File(pathPets);

        //Cria o arquivo caso não exista (Evita o erro FileNotFound)
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        //Construtores
        Scanner scan = new Scanner(System.in);
        //Pet
        MontarPet montarPet = new MontarPet();
        GerarNome gerarNome = new GerarNome();
        EscreverArquivo escreverArquivo = new EscreverArquivo();
        RespostasUsuario respostasUsuario = new RespostasUsuario();
        LerFormulario lerFormulario = new LerFormulario();
        //Formulário
        AtualizarForm atualizarForm = new AtualizarForm();

        //Menu principal
        MenuPrincipal.exibirMenu(
                pathFormulario,
                pathPets,
                diretorio,
                scan,
                montarPet,
                gerarNome,
                escreverArquivo,
                respostasUsuario,
                lerFormulario,
                atualizarForm
        );
    }
}
