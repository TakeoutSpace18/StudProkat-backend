package ru.studprokat.backend.dto.wallet;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CreateCouponDto extends CouponDto {
    @JsonProperty("money")
    private final Integer money;

    public CreateCouponDto(UUID id, Integer money) {
        super(id);
        this.money = money;
    }
    public Integer getMoney() {
        return money;
    }
}
