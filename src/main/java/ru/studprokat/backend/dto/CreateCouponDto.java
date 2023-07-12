package ru.studprokat.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CreateCouponDto extends CouponDto {
    @JsonProperty("money")
    private final int money;

    public CreateCouponDto(UUID coupon, int money) {
        super(coupon);
        this.money = money;
    }

    public int getMoney() {
        return money;
    }
}
