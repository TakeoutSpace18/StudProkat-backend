package ru.studprokat.backend.repository.cassandra.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("coupons")
public class Coupon {
    @PrimaryKey(value = "coupon")
    private UUID id;
    @Column
    private int money;
    @Column
    private boolean used;

    public UUID getId() {
        return id;
    }

    public Coupon setId(UUID id) {
        this.id = id;
        return this;
    }

    public int getMoney() {
        return money;
    }

    public Coupon setMoney(int money) {
        this.money = money;
        return this;
    }

    public boolean isUsed() {
        return used;
    }

    public Coupon setUsed(boolean used) {
        this.used = used;
        return this;
    }
}
