package ru.studprokat.backend.exception;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException() {
        super("You have no privilege to do that");
    }
}
