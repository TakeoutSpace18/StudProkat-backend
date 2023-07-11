package ru.studprokat.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.studprokat.backend.utils.PermissionLevel;

import java.util.UUID;

public class AuthSuccessDto {
    @JsonProperty("id")
    private final UUID id;
    @JsonProperty("permission_level")
    private final PermissionLevel permissionLevel;

    public AuthSuccessDto(UUID id, PermissionLevel permissionLevel) {
        this.id = id;
        this.permissionLevel = permissionLevel;
    }
}
