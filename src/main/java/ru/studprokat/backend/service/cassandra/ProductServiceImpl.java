package ru.studprokat.backend.service.cassandra;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.studprokat.backend.dto.ProductDto;
import ru.studprokat.backend.dto.UserLoginDto;
import ru.studprokat.backend.exception.ProductNotFoundException;
import ru.studprokat.backend.mappings.Mappings;
import ru.studprokat.backend.repository.cassandra.ProductsByAdTypeRepository;
import ru.studprokat.backend.repository.cassandra.ProductsByIdRepository;
import ru.studprokat.backend.repository.cassandra.ProductsByPriceRepository;
import ru.studprokat.backend.repository.cassandra.ProductsByProductTypeRepository;
import ru.studprokat.backend.repository.cassandra.entity.ProductsByAdType;
import ru.studprokat.backend.repository.cassandra.entity.ProductsById;
import ru.studprokat.backend.repository.cassandra.entity.ProductsByProductType;
import ru.studprokat.backend.service.ProductService;
import ru.studprokat.backend.utils.AdvertisementStatus;
import ru.studprokat.backend.utils.AdvertisementType;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public ProductDto create(ProductDto productDto, Authentication authentication) {

        //TODO добавить мапер
        ProductsById productsById = new ProductsById();
        UUID id = this.uuidGenerator.generateId(null);
        UserLoginDto userLoginDto = (UserLoginDto) authentication.getDetails();
        productsById.setId(id);
        productsById.setUserId(userLoginDto.getId());
        productsById.setProductDescription(productDto.getDescription());
        productsById.setAdType(productDto.getAdType());
        productsById.setAddress(productDto.getAddress());
        productsById.setCreationDate(LocalDate.now());
        productsById.setPhoto(productDto.getPhoto());
        productsById.setPrice(productDto.getPrice());
        productsById.setProductName(productDto.getName());
        productsById.setProductType(productDto.getProductType());
        productsById.setStatus(AdvertisementStatus.FREE);

        ProductsByAdType productsByAdType = new ProductsByAdType();
        productsByAdType.setId(id);
        productsByAdType.setUserId(userLoginDto.getId());
        productsByAdType.setProductDescription(productDto.getDescription());
        productsByAdType.setAdType(productDto.getAdType());
        productsByAdType.setAddress(productDto.getAddress());
        productsByAdType.setCreationDate(LocalDate.now());
        productsByAdType.setPhoto(productDto.getPhoto());
        productsByAdType.setPrice(productDto.getPrice());
        productsByAdType.setProductName(productDto.getName());
        productsByAdType.setProductType(productDto.getProductType());
        productsByAdType.setStatus(AdvertisementStatus.FREE);

        ProductsByProductType productsByProductType = new ProductsByProductType();
        productsByProductType.setId(id);
        productsByProductType.setUserId(userLoginDto.getId());
        productsByProductType.setProductDescription(productDto.getDescription());
        productsByProductType.setAdType(productDto.getAdType());
        productsByProductType.setAddress(productDto.getAddress());
        productsByProductType.setCreationDate(LocalDate.now());
        productsByProductType.setPhoto(productDto.getPhoto());
        productsByProductType.setPrice(productDto.getPrice());
        productsByProductType.setProductName(productDto.getName());
        productsByProductType.setProductType(productDto.getProductType());
        productsByProductType.setStatus(AdvertisementStatus.FREE);

        this.productsByIdRepository.save(productsById);
        this.productsByAdTypeRepository.save(productsByAdType);
        this.productsByProductTypeRepository.save(productsByProductType);

        return this.findById(id);
    }

    @Override
    public void delete(UUID id) {
        Optional<ProductsById> productsById = this.productsByIdRepository.findById(id);
        if (productsById.isEmpty()){
            throw new ProductNotFoundException();
        }

        this.productsByIdRepository.deleteById(id);
        this.productsByProductTypeRepository.deleteByKey_productTypeAndKey_Id(productsById.get().getProductType(),productsById.get().getId());
        this.productsByAdTypeRepository.deleteByKey_AdTypeAndKey_Id(productsById.get().getAdType(),productsById.get().getId());
    }

    @Override
    public List<ProductDto> findByAdType(AdvertisementType advertisementType) {
        List<ProductsByAdType> result = this.productsByAdTypeRepository.findByKey_adType(advertisementType);
        if (result.isEmpty()){
            throw new ProductNotFoundException();
        }
        return result.stream().map(Mappings::toProductsDTO).toList();
    }

    @Override
    public List<ProductDto> findByProductType(String productType) {
        List<ProductsByProductType> result = this.productsByProductTypeRepository.findByKey_productType(productType);
        if (result.isEmpty()){
            throw new ProductNotFoundException();
        }
        return result.stream().map(Mappings::toProductsDTO).toList();
    }

    @Override
    public ProductDto findById(UUID id) {
        Optional<ProductsById> productsById = this.productsByIdRepository.findById(id);
        if (productsById.isEmpty()){
            throw new ProductNotFoundException();
        }
        return Mappings.toProductsDTO(productsById.get());
    }

    @Override
    public Set<String> getProductTypes() {
        List<ProductsByProductType> result = this.productsByProductTypeRepository.findAll();
        if (result.isEmpty()){
            throw new ProductNotFoundException();
        }
        return result.stream().map(Mappings::toProductType).collect(Collectors.toSet());
    }
}
