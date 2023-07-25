package ru.studprokat.backend.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public class UserBaseDto {
        @JsonProperty("name")
        private final @NotEmpty String name;
        @JsonProperty("surname")
        private final @NotEmpty String surname;
        @JsonProperty("middle_name")
        private final String middleName;
        @JsonProperty("email")
        private final @Email String email;
        @JsonProperty("social_networks")
        private final Set<String> socialNetworks;

        public UserBaseDto(
                @NotEmpty
                @JsonProperty("name")
                String name,
                @NotEmpty
                @JsonProperty("surname")
                String surname,
                @JsonProperty("middle_name")
                String middleName,
                @Email
                @JsonProperty("email")
                String email,
                @JsonProperty("social_networks")
                Set<String> socialNetworks
        ) {
                this.name = name;
                this.surname = surname;
                this.middleName = middleName;
                this.email = email;
                this.socialNetworks = socialNetworks;
        }

        @JsonProperty("name")
        public @NotEmpty String getName() {
                return name;
        }

        @JsonProperty("surname")
        public @NotEmpty String getSurname() {
                return surname;
        }

        @JsonProperty("middle_name")
        public String getMiddleName() {
                return middleName;
        }

        @JsonProperty("email")
        public @Email String getEmail() {
                return email;
        }

        @JsonProperty("social_networks")
        public Set<String> getSocialNetworks() {
                return socialNetworks;
        }

}
