package main.services.pet;

import java.util.*;

public class RespostasUsuario {

    public List<String> respostas(LerFormulario lerFormulario, String pathFormulario, Scanner scan) {

        List<String> respostasPadrao = new ArrayList<>();
        Map<String, String> respostasExtra = new LinkedHashMap<>();

        List<String> perguntas = lerFormulario.ler(pathFormulario);

        for (int i = 0; i < perguntas.size(); i++) {
            System.out.println(perguntas.get(i));
            String resposta = scan.nextLine();

            if (i < 7) {
                respostasPadrao.add(resposta);
            } else {
                respostasExtra.put(perguntas.get(i), resposta);
            }
        }

        // unir tudo em um único conjunto
        List<String> respostasUnificadas = new ArrayList<>();
        respostasUnificadas.addAll(respostasPadrao);
        respostasUnificadas.addAll(respostasExtra.values());

        return respostasUnificadas;
    }
}
