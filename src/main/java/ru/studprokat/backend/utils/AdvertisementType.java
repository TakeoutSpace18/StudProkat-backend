package ru.studprokat.backend.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AdvertisementType {
    @JsonProperty("RENT")
    RENT, // сдать в аренду
    @JsonProperty("REQUEST")
    REQUEST, // запросить предмет
}
