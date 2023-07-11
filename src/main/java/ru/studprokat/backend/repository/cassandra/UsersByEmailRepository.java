package ru.studprokat.backend.repository.cassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import ru.studprokat.backend.repository.cassandra.entity.UsersByEmail;

import java.util.List;

@Repository
public interface UsersByEmailRepository extends CassandraRepository<UsersByEmail, String> {
}
