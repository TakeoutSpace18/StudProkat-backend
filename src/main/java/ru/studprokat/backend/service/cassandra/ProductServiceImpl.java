package ru.studprokat.backend.service.cassandra;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.studprokat.backend.dto.ProductDto;
import ru.studprokat.backend.exception.ProductNotFoundException;
import ru.studprokat.backend.mappings.Mappings;
import ru.studprokat.backend.repository.cassandra.ProductsByAdTypeRepository;
import ru.studprokat.backend.repository.cassandra.ProductsByIdRepository;
import ru.studprokat.backend.repository.cassandra.ProductsByPriceRepository;
import ru.studprokat.backend.repository.cassandra.ProductsByProductTypeRepository;
import ru.studprokat.backend.repository.cassandra.entity.ProductsById;
import ru.studprokat.backend.service.ProductService;
import ru.studprokat.backend.utils.AdvertisementStatus;
import ru.studprokat.backend.utils.AdvertisementType;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("cassandraProductService")
public class ProductServiceImpl implements ProductService {
    private final ProductsByIdRepository productsByIdRepository;
    private final ProductsByAdTypeRepository productsByAdTypeRepository;
    private final ProductsByPriceRepository productsByPriceRepository;
    private final ProductsByProductTypeRepository productsByProductTypeRepository;
    private final ObjectIdGenerators.UUIDGenerator uuidGenerator;

    @Autowired
    public ProductServiceImpl(ProductsByAdTypeRepository productsByAdTypeRepository, ProductsByPriceRepository productsByPriceRepository, ProductsByProductTypeRepository productsByProductTypeRepository, ProductsByIdRepository productsByIdRepository) {
        this.productsByAdTypeRepository = productsByAdTypeRepository;
        this.productsByPriceRepository = productsByPriceRepository;
        this.productsByProductTypeRepository = productsByProductTypeRepository;
        this.productsByIdRepository = productsByIdRepository;
        this.uuidGenerator = new ObjectIdGenerators.UUIDGenerator();
    }

    @Override
    public List<ProductDto> list() {
        List<ProductsById> result = this.productsByIdRepository.findAll();
        if (result.isEmpty()){
            throw new ProductNotFoundException();
        }
        return result.stream().map(Mappings::toProductsDTO).toList();
    }

    @Override
    public ProductDto create(ProductDto productDto) {
        //TODO добавить мапер и остальные таблицы
        ProductsById productsById = new ProductsById();
        UUID id = this.uuidGenerator.generateId(null);
        productsById.setId(id);
        productsById.setUserId(productDto.getUserId());
        productsById.setProductDescription(productDto.getDescription());
        productsById.setAdType(productDto.getAdType());
        productsById.setAddress(productDto.getAddress());
        productsById.setCreationDate(LocalDate.now());
        productsById.setPhoto(productDto.getPhoto());
        productsById.setPrice(productDto.getPrice());
        productsById.setProductName(productDto.getName());
        productsById.setProductType(productDto.getProductType());
        productsById.setStatus(AdvertisementStatus.FREE);

        this.productsByIdRepository.save(productsById);

        return this.findById(id);
    }

    @Override
    public void delete(UUID id) {
        Optional<ProductsById> productsById = this.productsByIdRepository.findById(id);
        if (productsById.isEmpty()){
            throw new ProductNotFoundException();
        }
        this.productsByIdRepository.deleteById(id);
        //TODO delete all product info too
    }

    @Override
    public ProductDto findByAdType(AdvertisementType advertisementType) {
        return null;
    }

    @Override
    public ProductDto findByProductType(String productType) {
        return null;
    }

    @Override
    public ProductDto findById(UUID id) {
        Optional<ProductsById> productsById = this.productsByIdRepository.findById(id);
        if (productsById.isEmpty()){
            throw new ProductNotFoundException();
        }
        return Mappings.toProductsDTO(productsById.get());
    }
}
