package com.getloc.syntech.getloc.mobile;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "device")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    @Id
    private String deviceId;
    private String deviceName;
    private String location;
    private GeoJsonPoint longitudeAndLatitude;

    public Device(String deviceName, String location, GeoJsonPoint longitudeAndLatitude) {
        this.deviceName = deviceName;
        this.location = location;
        this.longitudeAndLatitude = longitudeAndLatitude;
    }
}
