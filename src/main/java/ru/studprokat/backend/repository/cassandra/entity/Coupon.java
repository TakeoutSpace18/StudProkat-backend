package ru.studprokat.backend.repository.cassandra.entity;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("coupons")
public class Coupon {
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private UUID coupon;
    @Column
    private int money;
    @Column
    boolean used;

    public UUID getCoupon() {
        return coupon;
    }

    public Coupon setCoupon(UUID coupon) {
        this.coupon = coupon;
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
