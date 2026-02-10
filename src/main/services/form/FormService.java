package main.services.form;

import java.util.Scanner;

public class FormService {

    public static void adicionarPergunta(
            Scanner scan,
            String pathFormulario,
            AtualizarForm atualizarForm
    ) { atualizarForm.atualizar(scan, pathFormulario); }


}
