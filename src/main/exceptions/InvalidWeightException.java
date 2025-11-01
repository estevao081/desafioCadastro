package main.exceptions;

public class InvalidWeightException extends RuntimeException {

    public InvalidWeightException() {
        super("ERRO: Peso deve estar entre 0.5kg e 60kg");
    }

    public InvalidWeightException(String message) {
        super(message);
    }
}
