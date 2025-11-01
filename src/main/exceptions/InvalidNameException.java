package main.exceptions;

public class InvalidNameException extends RuntimeException {

    public InvalidNameException() {
        super("ERRO: Nome completo deve conter nome e sobrenome, somente letras de A-Z");
    }

    public InvalidNameException(String message) {
        super(message);
    }
}
