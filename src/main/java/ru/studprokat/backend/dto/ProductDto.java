package ru.studprokat.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.studprokat.backend.utils.AdvertisementStatus;
import ru.studprokat.backend.utils.AdvertisementType;
import ru.studprokat.backend.utils.RentTimeframe;

import java.time.LocalDate;
import java.util.UUID;

public class ProductDto {
        @JsonProperty("id")
        private final UUID id;
        @JsonProperty("user_id")
        private final UUID userId;
        @JsonProperty("ad_type")
        private final AdvertisementType adType;
        @JsonProperty("product_name")
        private final String name;
        @JsonProperty("product_type")
        private final String productType;
        @JsonProperty("product_description")
        private final String description;
        @JsonProperty("address")
        private final String address;
        @JsonProperty("creation_date")
        private final LocalDate creationDate;
        @JsonProperty("photo")
        private final String photo;
        @JsonProperty("price")
        private final int price;
//        @JsonProperty("rent_timeframe")
//        private final RentTimeframe timeframe;
        @JsonProperty("status")
        private final AdvertisementStatus status;

        public ProductDto(UUID id, UUID userId, AdvertisementType adType, String name, String productType, String description, String address, LocalDate creationDate, String photo, int price, AdvertisementStatus status) {
                this.id = id;
                this.userId = userId;
                this.adType = adType;
                this.name = name;
                this.productType = productType;
                this.description = description;
                this.address = address;
                this.creationDate = creationDate;
                this.photo = photo;
                this.price = price;
                this.status = status;
        }

        public UUID getId() {
                return id;
        }
        @JsonProperty("user_id")
        public UUID getUserId() {
                return userId;
        }
        @JsonProperty("ad_type")
        public AdvertisementType getAdType() {
                return adType;
        }
        @JsonProperty("product_name")
        public String getName() {
                return name;
        }
        @JsonProperty("product_type")
        public String getProductType() {
                return productType;
        }
        @JsonProperty("product_description")
        public String getDescription() {
                return description;
        }

        public String getAddress() {
                return address;
        }
        @JsonProperty("creation_date")
        public LocalDate getCreationDate() {
                return creationDate;
        }

        public String getPhoto() {
                return photo;
        }

        public int getPrice() {
                return price;
        }

        public AdvertisementStatus getStatus() {
                return status;
        }
}
