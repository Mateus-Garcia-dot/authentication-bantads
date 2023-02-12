package com.bantads.authentication.repository;

import com.bantads.authentication.model.AuthenticationModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AuthenticationRepository extends MongoRepository<AuthenticationModel, String> {
}
