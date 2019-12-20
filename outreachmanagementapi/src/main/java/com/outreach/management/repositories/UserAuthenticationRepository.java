package com.outreach.management.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.outreach.management.collections.UserCredentialsCollections;

@Repository
public interface UserAuthenticationRepository extends MongoRepository<UserCredentialsCollections, String> {
	
	UserCredentialsCollections findByEmail(String email);
}