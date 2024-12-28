package com.ramTech.ThriftWare.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ramTech.ThriftWare.models.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByMailId(String mailId);
}
