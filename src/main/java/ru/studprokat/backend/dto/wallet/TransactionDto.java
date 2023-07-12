package ru.studprokat.backend.dto.wallet;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public class TransactionDto {
    @NotEmpty
    @JsonProperty("client_id")
    private final UUID clientId;
    @NotEmpty
    @JsonProperty("money")
    private final Integer money;

    public TransactionDto(UUID clientId, int money) {
        this.clientId = clientId;
        this.money = money;
    }

    public UUID getClientId() {
        return clientId;
    }

    public Integer getMoney() {
        return money;
    }
}
