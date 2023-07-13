package ru.studprokat.backend.dto.wallet;

import jakarta.validation.constraints.Positive;

public record MoneyDto(@Positive int money) {}