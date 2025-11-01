package main.exceptions;

public class InvalidAgeException extends RuntimeException {

    public InvalidAgeException() {
        super("ERRO: Idade deve estar entre 0.1 e 20 anos");
    }

    public InvalidAgeException(String message) {
        super(message);
    }
}
