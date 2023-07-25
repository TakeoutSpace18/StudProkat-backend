package ru.studprokat.backend.mappings;

import ru.studprokat.backend.dto.HistoryDto;
import ru.studprokat.backend.dto.ProductDto;
import ru.studprokat.backend.dto.user.UserInputDto;
import ru.studprokat.backend.dto.user.UserOutputDto;
import ru.studprokat.backend.repository.cassandra.entity.*;


public class Mappings {
    public static String toProductType(ProductsByProductType entity) {
        return entity.getProductType();
    }

    public static HistoryDto toHistoryDto(HistoryByUser entity){
        return new HistoryDto(
                entity.getActive(),
                entity.getId(),
                entity.getClientId(),
                entity.getProductId(),
                entity.getUserId(),
                entity.getClientName(),
                entity.getClientSurname(),
                entity.getClientMiddleName(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getProductName()
        );
    }
    public static UserOutputDto toUserOutputDto(UsersById entity) {
        return new UserOutputDto(
                entity.getName(),
                entity.getSurname(),
                entity.getMiddleName(),
                entity.getEmail(),
                entity.getSocialNetworks(),
                entity.getId(),
                entity.getRegistrationDate()
        );
    }

    public static UsersById toUsersById(UserInputDto dto) {
        return new UsersById().setName(dto.getName())
                .setSurname(dto.getSurname())
                .setMiddleName(dto.getMiddleName())
                .setEmail(dto.getEmail())
                .setSocialNetworks(dto.getSocialNetworks());
    }

    public static ProductDto toProductsDTO(ProductsByAdType entity) {
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

    public static ProductDto toProductsDTO(ProductsById entity) {
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

    public static ProductDto toProductsDTO(ProductsByProductType entity) {
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

    public static ProductDto toProductsDTO(ProductsByPrice entity) {
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
