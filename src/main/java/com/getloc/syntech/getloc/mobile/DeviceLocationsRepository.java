package com.getloc.syntech.getloc.mobile;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceLocationsRepository extends MongoRepository<DeviceLocations, String>{
    
}
