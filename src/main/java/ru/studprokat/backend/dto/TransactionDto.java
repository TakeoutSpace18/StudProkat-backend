package ru.studprokat.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class TransactionDto {
    @JsonProperty("user_id")
    private final UUID userId;
    @JsonProperty("client_id")
    private final UUID clientId;
    @JsonProperty("money")
    private final int money;

    public TransactionDto(UUID userId, UUID clientId, int money) {
        this.userId = userId;
        this.clientId = clientId;
        this.money = money;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getClientId() {
        return clientId;
    }

    public int getMoney() {
        return money;
    }
}
