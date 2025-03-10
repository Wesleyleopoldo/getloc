package com.getloc.syntech.getloc.admin;

import org.bson.types.Binary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Binary>{
    User findByEmail(Binary userId);
}
