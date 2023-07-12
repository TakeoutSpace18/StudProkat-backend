package ru.studprokat.backend.repository.cassandra.entity;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;
import ru.studprokat.backend.utils.AdvertisementStatus;
import ru.studprokat.backend.utils.AdvertisementType;

import java.time.LocalDate;
import java.util.UUID;

@Table(value = "products_by_product_type")
public class ProductsByProductType {
    @PrimaryKey
    public Key key = new Key();

    @PrimaryKeyClass
    public static class Key {

        @PrimaryKeyColumn(name = "product_type", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
        private String  productType;
        @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.CLUSTERED, ordinal = 1)
        private UUID id;
        public Key() {
        }

        public Key(String  productType) {
            this.productType = productType;
        }
    }
    @Column(value = "user_id")
    private UUID userId;
    @Column(value = "ad_type")
    private AdvertisementType adType;
    @Column
    private String address;
    @Column(value = "creation_date")
    private LocalDate creationDate;
    @Column
    private String photo;
    @Column
    private int price;
    @Column(value = "product_description")
    private String productDescription;
    @Column(value = "product_name")
    private String productName;
    @Column
    private AdvertisementStatus status;
    public UUID getId() {
        return this.key.id;
    }

    public void setId(UUID id) {
        this.key.id = id;
    }




    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID id) {
        this.userId = id;
    }


    public String  getProductType() {
        return this.key.productType;
    }

    public void setProductType(String  productType) {
        this.key.productType = productType;
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


    public AdvertisementStatus getStatus() {
        return status;
    }

    public void setStatus(AdvertisementStatus status) {
        this.status = status;
    }


}
