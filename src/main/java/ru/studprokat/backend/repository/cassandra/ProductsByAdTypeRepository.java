package ru.studprokat.backend.repository.cassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;
import ru.studprokat.backend.repository.cassandra.entity.ProductsByAdType;
import ru.studprokat.backend.utils.AdvertisementType;

import java.util.List;
import java.util.UUID;

public interface ProductsByAdTypeRepository extends CassandraRepository<ProductsByAdType, ProductsByAdType.Key> {
    List<ProductsByAdType> findByKey_adType(AdvertisementType key_adType);
    void deleteByKey_AdTypeAndKey_Id(AdvertisementType key_adType, UUID key_id);
}
