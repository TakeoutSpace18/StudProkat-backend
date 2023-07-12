package ru.studprokat.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.studprokat.backend.utils.PermissionLevel;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public final class UserOutputDto extends UserBaseDto {
    @JsonProperty("id")
    private final UUID id;

    @JsonProperty("registration_date")
    private final LocalDate registrationDate;

    public UserOutputDto(String name, String surname, String middleName, String email, Set<String> socialNetworks, UUID id, LocalDate registrationDate) {
        super(name, surname, middleName, email, socialNetworks);
        this.id = id;
        this.registrationDate = registrationDate;
    }
}
