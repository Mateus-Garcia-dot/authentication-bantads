package com.bantads.authentication.repository;

import com.bantads.authentication.model.AuthenticationModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface AuthenticationRepository extends MongoRepository<AuthenticationModel, String> {
    // search by account
    @Query("{ 'account' : ?0 }")
    AuthenticationModel findByAccount(String account);
}
