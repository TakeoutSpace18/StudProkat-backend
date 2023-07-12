package ru.studprokat.backend.dto.wallet;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public class CouponDto {
    @NotEmpty
    @JsonProperty("coupon")
    private final UUID id;

    public CouponDto(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
