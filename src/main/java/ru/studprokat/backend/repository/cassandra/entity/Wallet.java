package ru.studprokat.backend.repository.cassandra.entity;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("wallet")
public class Wallet {
    @PrimaryKeyColumn(value = "user_id", type = PrimaryKeyType.PARTITIONED)
    private UUID userId;
    @Column
    private int money;

    public UUID getUserId() {
        return userId;
    }

    public Wallet setUserId(UUID userId) {
        this.userId = userId;
        return this;
    }

    public int getMoney() {
        return money;
    }

    public Wallet setMoney(int money) {
        this.money = money;
        return this;
    }
}
