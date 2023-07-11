package ru.studprokat.backend.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import ru.studprokat.backend.utils.PermissionLevel;

import java.util.Collection;
import java.util.UUID;

public class CustomUser extends User {
    private final UUID id;
    private final PermissionLevel permissionLevel;
    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, UUID id, PermissionLevel permissionLevel) {
        super(username, password, authorities);
        this.id = id;
        this.permissionLevel = permissionLevel;
    }

    public UUID getId() {
        return id;
    }

    public PermissionLevel getPermissionLevel() {
        return permissionLevel;
    }
}
