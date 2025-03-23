package com.getloc.syntech.getloc.mobile;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "devicelocations")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceLocations {
    
    @Id
    private String id;
    private String deviceName;
    private GeoJsonPoint longitudeAndLatitude;
    private LocalDateTime localDateTime;
}