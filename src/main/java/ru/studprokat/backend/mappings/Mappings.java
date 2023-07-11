package ru.studprokat.backend.mappings;

import ru.studprokat.backend.dto.UserInputDto;
import ru.studprokat.backend.dto.UserOutputDto;
import ru.studprokat.backend.repository.cassandra.entity.UsersById;


public class Mappings {
    public static UserOutputDto toUserOutputDto(UsersById entity) {
        return new UserOutputDto(
                entity.getName(),
                entity.getSurname(),
                entity.getMiddleName(),
                entity.getEmail(),
                entity.getSocialNetworks(),
                entity.getAddress(),
                entity.getId(),
                entity.getPermissionLevel(),
                entity.getRegistrationDate()
        );
    }

    public static UsersById toUsersById(UserInputDto dto) {
        return new UsersById().setName(dto.getName())
                .setSurname(dto.getSurname())
                .setMiddleName(dto.getMiddleName())
                .setEmail(dto.getEmail())
                .setAddress(dto.getAddress())
                .setSocialNetworks(dto.getSocialNetworks());
    }

}
