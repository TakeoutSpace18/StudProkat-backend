package ru.studprokat.backend.repository.cassandra.entity;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;
import ru.studprokat.backend.utils.AdvertisementStatus;
import ru.studprokat.backend.utils.AdvertisementType;

import java.time.LocalDate;
import java.util.UUID;

@Table(value = "products_by_price")
public class ProductsByPrice {

    @PrimaryKey
    public ProductsByPrice.Key key = new ProductsByPrice.Key();

    public UUID getId() {
        return this.key.id;
    }

    public void setId(UUID id) {
        this.key.id = id;
    }

    @PrimaryKeyClass
    public static class Key {
        @PrimaryKeyColumn(name = "price", type = PrimaryKeyType.PARTITIONED, ordinal = 1)
        private int price;
        @PrimaryKeyColumn(name = "user_id", type = PrimaryKeyType.CLUSTERED, ordinal = 0)
        private UUID userId;
        @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.CLUSTERED, ordinal = 2)
        private UUID id;

        public Key() {
        }

        public Key(int price) {
            this.price = price;
        }
    }

    @Column(value = "ad_type")
    private AdvertisementType adType;
    @Column
    private String address;
    @Column(value = "creation_date")
    private LocalDate creationDate;
    @Column
    private String photo;
    @Column(value = "product_description")
    private String productDescription;
    @Column(value = "product_name")
    private String productName;
    @Column(value = "product_type")
    private String productType;
    @Column
    private AdvertisementStatus status;


    public int getPrice() {
        return this.key.price;
    }

    public void setPrice(int price) {
        this.key.price = price;
    }


    public AdvertisementType getAdType() {
        return adType;
    }

    public void setAdType(AdvertisementType adType) {
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
