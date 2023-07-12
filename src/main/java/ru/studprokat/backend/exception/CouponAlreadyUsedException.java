package ru.studprokat.backend.exception;

public class CouponAlreadyUsedException extends RuntimeException {
    public CouponAlreadyUsedException() {
        super("Coupon already used");
    }
}
