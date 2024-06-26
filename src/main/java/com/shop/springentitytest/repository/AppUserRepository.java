package com.shop.springentitytest.repository;

import com.shop.springentitytest.entity.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
@EnableMongoRepositories
public interface AppUserRepository extends MongoRepository<AppUser,String> {
    boolean existsByUsername(String username);


    String save(String user);


    Optional<Object> findByUsername(String username);
}
