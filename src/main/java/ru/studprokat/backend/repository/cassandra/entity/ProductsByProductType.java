package ru.studprokat.backend.repository.cassandra.entity;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

@Table(value = "products_by_product_type")
public class ProductsByProductType {
    @PrimaryKey
    public Key key = new Key();

    @PrimaryKeyClass
    public static class Key {
        @PrimaryKeyColumn(name = "product_type", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
        private String productType;
        @PrimaryKeyColumn(name = "user_id", type = PrimaryKeyType.CLUSTERED, ordinal = 1)
        private String userId;

        public Key() {
        }

        public Key(String productType) {
            this.productType = productType;
        }
    }

    @Column
    private String adType;
    @Column
    private String address;
    @Column
    private java.sql.Date creationDate;
    @Column
    private String photo;
    @Column
    private long price;
    @Column
    private String productDescription;
    @Column
    private String productName;
    @Column
    private String status;


    public String getId() {
        return this.key.userId;
    }

    public void setId(String id) {
        this.key.userId = id;
    }


    public String getProductType() {
        return this.key.productType;
    }

    public void setProductType(String productType) {
        this.key.productType = productType;
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


    public java.sql.Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(java.sql.Date creationDate) {
        this.creationDate = creationDate;
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
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


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
