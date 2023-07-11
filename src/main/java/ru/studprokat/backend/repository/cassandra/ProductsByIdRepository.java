package ru.studprokat.backend.repository.cassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;
import ru.studprokat.backend.repository.cassandra.entity.ProductsById;

import java.util.UUID;

public interface ProductsByIdRepository extends CassandraRepository<ProductsById, UUID> {
}
