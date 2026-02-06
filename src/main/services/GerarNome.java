package main.services;

import java.time.LocalDateTime;

public class GerarNome {

    public String gerar(String nome) {

        String data = LocalDateTime.now().toString()
                .replace("-", "")
                .replace(":", "")
                .substring(0, 13);

        return data + "-" + nome.toUpperCase().replaceAll("\\s+", "") + ".TXT";
    }
}
