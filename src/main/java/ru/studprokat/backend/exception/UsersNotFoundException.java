package ru.studprokat.backend.exception;

public class UsersNotFoundException extends NotFoundException {
    public UsersNotFoundException() {
        // TODO: localization
        super("Users not found");
    }
}
