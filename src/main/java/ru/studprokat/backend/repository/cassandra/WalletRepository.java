package ru.studprokat.backend.repository.cassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;
import ru.studprokat.backend.repository.cassandra.entity.Wallet;

import java.util.UUID;

public interface WalletRepository extends CassandraRepository<Wallet, UUID> {
}
