package com.getloc.syntech.getloc.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.getloc.syntech.getloc.admin.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
    Optional<User> findByEmail(String userEmail);
}
