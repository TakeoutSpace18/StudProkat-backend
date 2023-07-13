package ru.studprokat.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.UUID;

public class HistoryDto {
    private final boolean active;
    private final UUID id;
    @JsonProperty(value = "client_id")
    private final UUID clientId;
    @JsonProperty(value = "product_id")
    private final UUID productId;
    @JsonProperty(value = "user_id")
    private final UUID userId;
    @JsonProperty(value = "client_name")
    private final String clientName;
    @JsonProperty(value = "client_surname")
    private final String clientSurname;
    @JsonProperty(value = "client_middle_name")
    private final String clientMiddleName;
    @JsonProperty(value = "start_date")
    private final LocalDate startDate;
    @JsonProperty(value = "end_date")
    private final LocalDate endDate;
    @JsonProperty(value = "product_name")
    private final String productName;

    public HistoryDto(boolean active, UUID id, UUID clientId, UUID productId, UUID userId, String clientName, String clientSurname, String clientMiddleName, LocalDate startDate, LocalDate endDate, String productName) {
        this.active = active;
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.userId = userId;
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.clientMiddleName = clientMiddleName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.productName = productName;
    }

    public boolean isActive() {
        return active;
    }

    public UUID getId() {
        return id;
    }

    public UUID getClientId() {
        return clientId;
    }

    public UUID getProductId() {
        return productId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public String getClientMiddleName() {
        return clientMiddleName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getProductName() {
        return productName;
    }
}
