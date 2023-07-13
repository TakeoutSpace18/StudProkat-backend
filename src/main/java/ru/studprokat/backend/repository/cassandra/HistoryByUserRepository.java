package ru.studprokat.backend.repository.cassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import ru.studprokat.backend.repository.cassandra.entity.HistoryByUser;

import java.util.UUID;


public interface HistoryByUserRepository extends CassandraRepository<HistoryByUser, HistoryByUser.Key> {
    HistoryByUser findByKey_ActiveAndKey_Id(boolean key_active, UUID key_id);
}
