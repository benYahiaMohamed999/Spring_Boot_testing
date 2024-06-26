package com.shop.springentitytest.repository;

import com.shop.springentitytest.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableMongoRepositories
public interface UserRepository extends MongoRepository<UserEntity,String> {
}