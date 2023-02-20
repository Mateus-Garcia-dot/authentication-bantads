package com.bantads.authentication.repository;

import com.bantads.authentication.model.AuthenticationModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface AuthenticationRepository extends MongoRepository<AuthenticationModel, String> {
    // search by account
    @Query("{ 'customer' : ?0 }")
    AuthenticationModel findByCustomer(String account);
    @Query("{ 'login' : ?0, 'password' : ?1 }")
    AuthenticationModel findByLoginAndPassword(String login, String password);
}
