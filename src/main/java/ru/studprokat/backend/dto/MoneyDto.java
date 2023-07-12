package ru.studprokat.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MoneyDto(@JsonProperty("money") int money) {
}
