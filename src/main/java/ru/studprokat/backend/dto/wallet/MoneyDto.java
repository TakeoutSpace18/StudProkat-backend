package ru.studprokat.backend.dto.wallet;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class MoneyDto {
    @JsonProperty("money")
    private final Integer money;

    public MoneyDto(Integer money) {
        this.money = money;
    }

    public Integer getMoney() {
        return money;
    }

}
