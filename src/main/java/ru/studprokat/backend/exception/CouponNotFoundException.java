package ru.studprokat.backend.exception;

public class CouponNotFoundException extends NotFoundException {
    public CouponNotFoundException() {
        super("Coupon was not found");
    }
}
