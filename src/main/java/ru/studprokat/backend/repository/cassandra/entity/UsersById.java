package ru.studprokat.backend.repository.cassandra.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import ru.studprokat.backend.utils.PermissionLevel;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Table("users_by_id")
public class UsersById {
    @Id
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private UUID id;
    @Column
    private String email;
    @Column
    private String name;
    @Column
    private String surname;
    @Column(value = "middle_name")
    private String middleName;
    @Column(value = "social_networks")
    private Set<String> socialNetworks;
    @Column(value = "registration_date")
    private LocalDate registrationDate;

    public UsersById setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UsersById setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public UsersById setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public UsersById setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public UsersById setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public Set<String> getSocialNetworks() {
        return socialNetworks;
    }

    public UsersById setSocialNetworks(Set<String> socialNetworks) {
        this.socialNetworks = socialNetworks;
        return this;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public UsersById setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }

    public UUID getId() {
        return id;
    }
}
