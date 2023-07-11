package ru.studprokat.backend.repository.cassandra.entity;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;
import ru.studprokat.backend.utils.AdvertisementStatus;
import ru.studprokat.backend.utils.AdvertisementType;

import java.time.LocalDate;
import java.util.UUID;

@Table(value = "products_by_ad_type")
public class ProductsByAdType {
    @PrimaryKey
    public ProductsByAdType.Key key = new ProductsByAdType.Key();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @PrimaryKeyClass
    public static class Key {
        @PrimaryKeyColumn(name = "ad_type", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
        private AdvertisementType adType;
        @PrimaryKeyColumn(name = "user_id", type = PrimaryKeyType.CLUSTERED, ordinal = 1)
        private UUID userId;

        public Key() {
        }

        public Key(AdvertisementType adType) {
            this.adType = adType;
        }
    }

    @Column
    private String address;
    @Column
    private LocalDate creationDate;
    @Column
    private String photo;
    @Column
    private int price;
    @Column
    private String productDescription;
    @Column
    private String productName;
    @Column
    private String productType;
    @Column
    private AdvertisementStatus status;
    @Column
    private UUID id;


    public AdvertisementType getAdType() {
        return this.key.adType;
    }

    public void setAdType(AdvertisementType adType) {
        this.key.adType = adType;
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


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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


    public AdvertisementStatus getStatus() {
        return status;
    }

    public void setStatus(AdvertisementStatus status) {
        this.status = status;
    }


    public UUID getUserId() {
        return this.key.userId;
    }

    public void setUserId(UUID userId) {
        this.key.userId = userId;
    }

}
