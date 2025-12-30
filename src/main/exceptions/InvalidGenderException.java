package main.exceptions;

public class InvalidGenderException extends RuntimeException {

    public InvalidGenderException() {
        super("ERRO: Gênero inválido");
    }

    public InvalidGenderException(String message) {
        super(message);
    }
}
