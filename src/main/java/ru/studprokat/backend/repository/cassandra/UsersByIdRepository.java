package ru.studprokat.backend.repository.cassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import ru.studprokat.backend.repository.cassandra.entity.UsersById;

import java.util.UUID;

@Repository
public interface UsersByIdRepository extends CassandraRepository<UsersById, UUID> {}
