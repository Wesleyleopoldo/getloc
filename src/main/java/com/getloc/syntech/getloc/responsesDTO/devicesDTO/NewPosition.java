package com.getloc.syntech.getloc.responsesDTO.devicesDTO;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public record NewPosition(String deviceName, String deviceLocation, GeoJsonPoint longitudeAndLatitude) {
}
