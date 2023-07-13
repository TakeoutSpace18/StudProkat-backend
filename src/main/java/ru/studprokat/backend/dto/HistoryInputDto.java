package ru.studprokat.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.UUID;

public class HistoryInputDto {
    @JsonProperty(value = "product_id")
    private final UUID productId;
    @JsonProperty(value = "client_id")
    private final UUID clientId;
    @JsonProperty(value = "end_date")
    private final LocalDate endDate;

    public HistoryInputDto(UUID productId, UUID clientId, LocalDate endDate) {
        this.productId = productId;
        this.clientId = clientId;
        this.endDate = endDate;
    }

    public UUID getProductId() {
        return productId;
    }

    public UUID getClientId() {
        return clientId;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
