package ru.studprokat.backend.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AdvertisementType {
    @JsonProperty("rent")
    RENT, // сдать в аренду
    @JsonProperty("request")
    REQUEST // запросить предмет
}
