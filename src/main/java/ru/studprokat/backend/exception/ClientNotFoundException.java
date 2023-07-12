package ru.studprokat.backend.exception;

public class ClientNotFoundException extends NotFoundException {

    public ClientNotFoundException() {
        super("Client not found");
    }
}
