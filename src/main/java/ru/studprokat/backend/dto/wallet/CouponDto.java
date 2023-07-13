package ru.studprokat.backend.dto.wallet;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CouponDto {
    @JsonProperty("coupon")
    private final UUID id;

    public CouponDto(@JsonProperty("coupon") UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
