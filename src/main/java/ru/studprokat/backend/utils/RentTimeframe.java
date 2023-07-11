package ru.studprokat.backend.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RentTimeframe {
    @JsonProperty("hour")
    HOUR,
    @JsonProperty("day")
    DAY,
    @JsonProperty("week")
    WEEK
}
