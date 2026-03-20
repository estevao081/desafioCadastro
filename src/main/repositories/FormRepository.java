package main.repositories;

public interface FormRepository {

    void adicionar(String pergunta);

    void alterar(int campo, String novoValor);

    void excluir(int indicePergunta);
}
