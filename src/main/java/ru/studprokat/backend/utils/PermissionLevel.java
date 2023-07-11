package ru.studprokat.backend.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PermissionLevel {
    @JsonProperty("user")
    USER,
    @JsonProperty("admin")
    ADMIN
}
