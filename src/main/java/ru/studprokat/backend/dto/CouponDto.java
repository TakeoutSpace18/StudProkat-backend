package ru.studprokat.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CouponDto {
    @JsonProperty("coupon")
    private final UUID coupon;

    public CouponDto(UUID coupon) {
        this.coupon = coupon;
    }

    public UUID getCoupon() {
        return coupon;
    }
}
