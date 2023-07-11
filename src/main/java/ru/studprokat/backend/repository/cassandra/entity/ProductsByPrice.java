package ru.studprokat.backend.repository.cassandra.entity;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.time.LocalDate;
import java.util.UUID;

@Table(value = "products_by_price")
public class ProductsByPrice {

    @PrimaryKey
    public ProductsByPrice.Key key = new ProductsByPrice.Key();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @PrimaryKeyClass
    public static class Key {
        @PrimaryKeyColumn(name = "price", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
        private long price;
        @PrimaryKeyColumn(name = "user_id", type = PrimaryKeyType.CLUSTERED, ordinal = 1)
        private String userId;

        public Key() {
        }

        public Key(long price) {
            this.price = price;
        }
    }

    @Column
    private String adType;
    @Column
    private String address;
    @Column
    private LocalDate creationDate;
    @Column
    private String photo;
    @Column
    private String productDescription;
    @Column
    private String productName;
    @Column
    private String productType;
    @Column
    private String status;
    @Column
    private UUID id;


    public long getPrice() {
        return this.key.price;
    }

    public void setPrice(long price) {
        this.key.price = price;
    }


    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getUserId() {
        return this.key.userId;
    }

    public void setUserId(String userId) {
        this.key.userId = userId;
    }

}
