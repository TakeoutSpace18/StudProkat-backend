package ru.studprokat.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.studprokat.backend.utils.PermissionLevel;

import java.util.UUID;

public class UserLoginDto {
    private final UUID id;
    private final String email;
    private final String password;
    @JsonProperty("permission_level")
    private final PermissionLevel permissionLevel;

    public UserLoginDto(UUID id, String email, String password, PermissionLevel permissionLevel) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.permissionLevel = permissionLevel;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public PermissionLevel getPermissionLevel() {
        return permissionLevel;
    }
}
