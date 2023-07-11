package ru.studprokat.backend.repository.cassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import ru.studprokat.backend.repository.cassandra.entity.UsersByEmail;

@Repository
public interface UserIdByEmailRepository extends CassandraRepository<UsersByEmail, String> {}
