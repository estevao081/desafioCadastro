package main.services.form;

import main.repositories.FormRepository;

public class FormService {

    private final FormRepository formRepository;

    public FormService(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    public void adicionar(String pergunta) {
        formRepository.adicionar(pergunta);
    }

    public void alterar(int campo, String novoValor) {
        formRepository.alterar(campo, novoValor);
    }

    public void excluir(int indice) {
        formRepository.excluir(indice);
    }
}
