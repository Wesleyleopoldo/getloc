package com.getloc.syntech.getloc.requests.devices;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public record NewDevice(String devicename, String location, GeoJsonPoint longitudeAndLatitude) {
}
