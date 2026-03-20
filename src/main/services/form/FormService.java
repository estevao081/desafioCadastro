package main.services.form;

import main.repositories.FormRepository;

public class FormService {

    private final FormRepository formRepository;

    public FormService(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    public void adicionar(String resposta) {
        formRepository.adicionar(resposta);
    }

    public void alterar() {

    }
}
