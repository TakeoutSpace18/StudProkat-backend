package ru.studprokat.backend.repository.cassandra.entity;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "products_by_ad_type")
public class ProductsByAdType {
    @PrimaryKey
    public ProductsByAdType.Key key = new ProductsByAdType.Key();

    @PrimaryKeyClass
    public static class Key {
        @PrimaryKeyColumn(name = "ad_type", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
        private String adType;
        @PrimaryKeyColumn(name = "user_id", type = PrimaryKeyType.CLUSTERED, ordinal = 1)
        private String userId;

        public Key() {
        }

        public Key(String adType) {
            this.adType = adType;
        }
    }

    private String address;
    private java.sql.Date creationDate;
    private String photo;
    private long price;
    private String productDescription;
    private String productName;
    private String productType;
    private String status;


    public String getAdType() {
        return this.key.adType;
    }

    public void setAdType(String adType) {
        this.key.adType = adType;
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
