package ru.studprokat.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ValidationExceptionDto(
        @JsonProperty("field_name")
        String fieldName,
        @JsonProperty("message")
        String message
) {}
