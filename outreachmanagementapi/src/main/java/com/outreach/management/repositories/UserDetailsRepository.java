package com.outreach.management.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.outreach.management.collections.UserDetailsCollections;

@Repository
public interface UserDetailsRepository extends MongoRepository<UserDetailsCollections, String> {
	
	UserDetailsCollections findByEmailAddress(String emailAddress);

}
