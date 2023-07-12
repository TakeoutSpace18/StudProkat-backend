package ru.studprokat.backend.service;

import org.springframework.security.core.Authentication;
import ru.studprokat.backend.dto.ProductDto;
import ru.studprokat.backend.utils.AdvertisementType;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<ProductDto> list();
    ProductDto create(ProductDto productDto, Authentication authentication);
    void delete (UUID id);
    ProductDto findByAdType(AdvertisementType advertisementType);
    ProductDto findByProductType(String productType);
    ProductDto findById(UUID id);
}
