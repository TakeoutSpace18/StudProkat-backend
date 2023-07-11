package ru.studprokat.backend.mappings;

import ru.studprokat.backend.dto.ProductDto;
import ru.studprokat.backend.dto.UserInputDto;
import ru.studprokat.backend.dto.UserOutputDto;
import ru.studprokat.backend.repository.cassandra.entity.ProductsByAdType;
import ru.studprokat.backend.repository.cassandra.entity.ProductsById;
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

    public static ProductDto toProductsDTO(ProductsByAdType entity){
        return new ProductDto(
                entity.getId(),
                entity.getUserId(),
                entity.getAdType(),
                entity.getProductName(),
                entity.getProductType(),
                entity.getProductDescription(),
                entity.getAddress(),
                entity.getCreationDate(),
                entity.getPhoto(),
                entity.getPrice(),
                entity.getStatus()
        );
    }
    public static ProductDto toProductsDTO(ProductsById entity){
        return new ProductDto(
                entity.getId(),
                entity.getUserId(),
                entity.getAdType(),
                entity.getProductName(),
                entity.getProductType(),
                entity.getProductDescription(),
                entity.getAddress(),
                entity.getCreationDate(),
                entity.getPhoto(),
                entity.getPrice(),
                entity.getStatus()
        );
    }
}
