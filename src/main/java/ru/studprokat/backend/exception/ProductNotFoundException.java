package ru.studprokat.backend.exception;

public class ProductNotFoundException extends NotFoundException{
    public ProductNotFoundException() {
        super("Products not found");
    }
}
