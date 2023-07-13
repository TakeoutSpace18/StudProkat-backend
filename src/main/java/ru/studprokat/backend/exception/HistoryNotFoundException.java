package ru.studprokat.backend.exception;

public class HistoryNotFoundException extends NotFoundException{
    public HistoryNotFoundException() {
        super("History does not exist.");
    }
}
