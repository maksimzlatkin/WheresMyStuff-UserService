package com.stuff.location_service.repository;

import com.stuff.location_service.model.User;

import java.util.List;
import java.util.Map;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}
