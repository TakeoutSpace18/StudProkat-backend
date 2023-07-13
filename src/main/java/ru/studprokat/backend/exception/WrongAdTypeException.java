package ru.studprokat.backend.exception;

public class WrongAdTypeException extends RuntimeException{

    public WrongAdTypeException() {
        super("Can't change status of request.");
    }
}
