package main.exceptions;

public class InvalidRaceException extends RuntimeException {

    public InvalidRaceException() {
        super("ERRO: Raça não pode conter números nem caracteres especiais");
    }

    public InvalidRaceException(String message) {
        super(message);
    }
}
