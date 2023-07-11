package ru.studprokat.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.studprokat.backend.utils.AdvertisementType;
import ru.studprokat.backend.utils.RentTimeframe;

public record ProductDto(
        // TODO: user_id, creation_date
        @JsonProperty("ad_type")
        AdvertisementType adType,
        @JsonProperty("product_name")
        String name,
        @JsonProperty("product_type")
        String category,
        @JsonProperty("description")
        String description,
        @JsonProperty("address")
        String address,
        @JsonProperty("photo")
        String photo,
        @JsonProperty("price")
        int price,
        @JsonProperty("timeframe")
        RentTimeframe timeframe
) {}
