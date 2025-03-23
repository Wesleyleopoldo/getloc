package com.getloc.syntech.getloc.requests.devices;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public record NewPositionData(String location, GeoJsonPoint longitudeAndLatitude, LocalDateTime dateTime) {
}
