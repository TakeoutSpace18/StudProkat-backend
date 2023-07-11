package ru.studprokat.backend.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AdvertisementStatus {
    @JsonProperty("busy")
    BUSY,   //занято
    @JsonProperty("free")
    FREE    //свободно
}
