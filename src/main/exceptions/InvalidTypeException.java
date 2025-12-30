package main.exceptions;

public class InvalidTypeException extends RuntimeException {

    public InvalidTypeException() {
        super("ERRO: Tipo inválido");
    }

    public InvalidTypeException(String message) {
        super(message);
    }
}
