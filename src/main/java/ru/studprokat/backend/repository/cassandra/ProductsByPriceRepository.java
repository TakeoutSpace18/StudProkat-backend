package ru.studprokat.backend.repository.cassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;
import ru.studprokat.backend.repository.cassandra.entity.ProductsByPrice;
import ru.studprokat.backend.repository.cassandra.entity.ProductsByProductType;

import java.util.List;

public interface ProductsByPriceRepository extends CassandraRepository<ProductsByPrice, ProductsByPrice.Key> {

}
