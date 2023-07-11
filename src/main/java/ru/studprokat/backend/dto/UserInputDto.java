package ru.studprokat.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class UserInputDto extends UserBaseDto {
    @JsonProperty("password")
    private final String password;
    public UserInputDto(String name, String surname, String middleName, String email, Set<String> socialNetworks, String address, String password) {
        super(name, surname, middleName, email, socialNetworks, address);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}

