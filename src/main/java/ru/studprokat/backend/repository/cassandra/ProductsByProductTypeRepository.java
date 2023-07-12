package ru.studprokat.backend.repository.cassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;
import ru.studprokat.backend.repository.cassandra.entity.ProductsByProductType;
import ru.studprokat.backend.utils.AdvertisementType;

import java.util.List;
import java.util.UUID;

public interface ProductsByProductTypeRepository extends CassandraRepository<ProductsByProductType, ProductsByProductType.Key> {
    List<ProductsByProductType> findByKey_productType(AdvertisementType key_productType);
    void deleteByKey_productTypeAndKey_Id(String key_productType, UUID key_id);
}
