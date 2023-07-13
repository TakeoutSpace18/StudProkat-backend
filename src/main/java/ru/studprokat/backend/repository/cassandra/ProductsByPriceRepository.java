package ru.studprokat.backend.repository.cassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;
import ru.studprokat.backend.repository.cassandra.entity.ProductsByPrice;
import ru.studprokat.backend.repository.cassandra.entity.ProductsByProductType;

import java.util.List;
import java.util.UUID;

public interface ProductsByPriceRepository extends CassandraRepository<ProductsByPrice, ProductsByPrice.Key> {
    List<ProductsByPrice> findByKey_UserId(UUID key_userId);
    ProductsByPrice findByKey_UserIdAndKey_PriceAndKey_Id(UUID key_userId, int key_price, UUID key_id);
    void deleteByKey_UserIdAndKey_PriceAndKey_Id(UUID key_userId, int key_price, UUID key_id);
}
