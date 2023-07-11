package ru.studprokat.backend.repository.cassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;
import ru.studprokat.backend.repository.cassandra.entity.ProductsByProductType;

import java.util.List;

public interface ProductsByProductTypeRepository extends CassandraRepository<ProductsByProductType, ProductsByProductType.Key> {
    List<ProductsByProductType> findByKey_productType(String key_productType);
}
