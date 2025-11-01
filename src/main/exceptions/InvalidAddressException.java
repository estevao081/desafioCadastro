package main.exceptions;

public class InvalidAddressException extends RuntimeException {

    public InvalidAddressException() {
        super("ERRO: Endereço inválido");
    }

    public InvalidAddressException(String message) {
        super(message);
    }
}
