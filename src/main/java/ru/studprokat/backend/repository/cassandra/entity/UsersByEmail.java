package ru.studprokat.backend.repository.cassandra.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import ru.studprokat.backend.utils.PermissionLevel;

import java.time.LocalDate;
import java.util.UUID;

@Table("users_by_mail")
public class UsersByEmail {
    @Id
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private String email;
    @Column
    private UUID id;
    @Column(value = "permission_level")
    private PermissionLevel permissionLevel;
    @Column
    private String password;
    @Column(value = "registration_date")
    private LocalDate registrationDate;

    public PermissionLevel getPermissionLevel() {
        return permissionLevel;
    }

    public UsersByEmail setPermissionLevel(PermissionLevel permissionLevel) {
        this.permissionLevel = permissionLevel;
        return this;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public UsersByEmail setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UsersByEmail setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UsersByEmail setEmail(String email) {
        this.email = email;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public UsersByEmail setId(UUID id) {
        this.id = id;
        return this;
    }
}
