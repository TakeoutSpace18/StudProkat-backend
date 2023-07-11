package ru.studprokat.backend.repository.cassandra.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;
import ru.studprokat.backend.utils.AdvertisementStatus;
import ru.studprokat.backend.utils.AdvertisementType;

import java.time.LocalDate;
import java.util.UUID;
@Table(value = "products_by_id")
public class ProductsById {
  @Id
  @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
  private UUID id;
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
  @Column(value = "product_type")
  private String productType;
  @Column
  private AdvertisementStatus status;


  public UUID getId() {
    return this.id;
  }

  public void setId(UUID id) {
    this.id = id;
  }


  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
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

}
