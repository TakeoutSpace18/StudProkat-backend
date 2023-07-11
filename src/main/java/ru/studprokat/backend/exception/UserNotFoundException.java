package ru.studprokat.backend.exception;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        // TODO: localization
        super("User not found");
    }
}
