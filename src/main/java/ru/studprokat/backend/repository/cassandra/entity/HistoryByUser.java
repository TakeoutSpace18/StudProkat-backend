package ru.studprokat.backend.repository.cassandra.entity;

import jnr.constants.platform.Local;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;
import ru.studprokat.backend.utils.AdvertisementStatus;
import ru.studprokat.backend.utils.AdvertisementType;

import java.time.LocalDate;
import java.util.UUID;

@Table(value = "history_by_user")
public class HistoryByUser {


  @PrimaryKey
  public HistoryByUser.Key key = new HistoryByUser.Key();

  @PrimaryKeyClass
  public static class Key {
    @PrimaryKeyColumn(name = "active", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
    private boolean active;
    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.CLUSTERED, ordinal = 1)
    private UUID id;

    public Key() {
    }

    public Key(UUID id) {
      this.id = id;
    }
  }
  @Column(value = "client_id")
  private UUID clientId;
  @Column(value = "client_middle_name")
  private String clientMiddleName;
  @Column(value = "client_name")
  private String clientName;
  @Column(value = "client_surname")
  private String clientSurname;
  @Column(value = "end_date")
  private LocalDate endDate;
  @Column(value = "product_id")
  private UUID productId;
  @Column(value = "product_name")
  private String productName;
  @Column(value = "start_date")
  private LocalDate startDate;
  @Column(value = "user_id")
  private UUID userId;


  public boolean getActive() {
    return this.key.active;
  }

  public void setActive(boolean active) {
    this.key.active = active;
  }


  public UUID getId() {
    return this.key.id;
  }

  public void setId(UUID id) {
    this.key.id = id;
  }


  public UUID getClientId() {
    return clientId;
  }

  public void setClientId(UUID clientId) {
    this.clientId = clientId;
  }


  public String getClientMiddleName() {
    return clientMiddleName;
  }

  public void setClientMiddleName(String clientMiddleName) {
    this.clientMiddleName = clientMiddleName;
  }


  public String getClientName() {
    return clientName;
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
  }


  public String getClientSurname() {
    return clientSurname;
  }

  public void setClientSurname(String clientSurname) {
    this.clientSurname = clientSurname;
  }


  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }


  public UUID getProductId() {
    return productId;
  }

  public void setProductId(UUID productId) {
    this.productId = productId;
  }


  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }


  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }


  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

}
