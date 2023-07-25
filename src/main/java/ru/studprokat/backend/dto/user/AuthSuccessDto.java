package ru.studprokat.backend.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.studprokat.backend.utils.PermissionLevel;

import java.time.LocalDate;
import java.util.UUID;

public class AuthSuccessDto {
    @JsonProperty("id")
    private final UUID id;
    @JsonProperty("permission_level")
    private final PermissionLevel permissionLevel;

    @JsonProperty("registration_date")
    private final LocalDate registrationDate;

    public AuthSuccessDto(UUID id, PermissionLevel permissionLevel, LocalDate registrationDate) {
        this.id = id;
        this.permissionLevel = permissionLevel;
        this.registrationDate = registrationDate;
    }
}
