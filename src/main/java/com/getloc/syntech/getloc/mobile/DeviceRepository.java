package com.getloc.syntech.getloc.mobile;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends MongoRepository<Device, String>{
    
    Optional<Device> findByDeviceName(String deviceName);
}
