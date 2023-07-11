package ru.studprokat.backend.repository.cassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;
import ru.studprokat.backend.repository.cassandra.entity.ProductsByAdType;

import java.util.List;

public interface ProductsByAdTypeRepository extends CassandraRepository<ProductsByAdType, ProductsByAdType.Key> {
    List<ProductsByAdType> findByKey_adType(String key_adType);
}
