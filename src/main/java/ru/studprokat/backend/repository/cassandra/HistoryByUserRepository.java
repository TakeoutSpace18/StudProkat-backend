package ru.studprokat.backend.repository.cassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;
import ru.studprokat.backend.repository.cassandra.entity.HistoryByUser;

import java.util.List;
import java.util.UUID;


public interface HistoryByUserRepository extends CassandraRepository<HistoryByUser, HistoryByUser.Key> {
    HistoryByUser findByKey_ActiveAndKey_Id(boolean key_active, UUID key_id);
    List<HistoryByUser> findAllByKey_UserId(UUID userId);
}
