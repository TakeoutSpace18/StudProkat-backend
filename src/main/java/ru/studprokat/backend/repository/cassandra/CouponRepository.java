package ru.studprokat.backend.repository.cassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;
import ru.studprokat.backend.repository.cassandra.entity.Coupon;

import java.util.UUID;

public interface CouponRepository extends CassandraRepository<Coupon, UUID> {
}
