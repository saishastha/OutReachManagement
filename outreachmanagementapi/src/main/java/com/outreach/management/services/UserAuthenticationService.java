package com.outreach.management.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.outreach.management.collections.DatabaseSequence;
import com.outreach.management.collections.UserCredentialsCollections;
import com.outreach.management.collections.UserDetailsCollections;
import com.outreach.management.dto.UserCredentialsDTO;
import com.outreach.management.dto.UserDetailsDTO;
import com.outreach.management.repositories.UserAuthenticationRepository;
import com.outreach.management.repositories.UserDetailsRepository;

@Service
public class UserAuthenticationService {

	@Autowired
	private UserAuthenticationRepository userAuthenticationRepository;
	
	private static final String HOSTING_SEQ_KEY = "hosting";

	@Autowired
	private MongoOperations mongoOperation;

	@Autowired
	private UserDetailsRepository userDetailsRepository;
	


	public UserDetailsCollections authenticateUser(UserCredentialsDTO userCredentialsDTO) {
		System.out.println(userCredentialsDTO.getEmail());
		UserCredentialsCollections userCredentials = userAuthenticationRepository
				.findByEmail(userCredentialsDTO.getEmail());
		System.out.println(userCredentials);
		// System.out.println(userCredentials.getRole());
		if (Objects.nonNull(userCredentials)) {
			if (userCredentials.getPassword().equals(userCredentialsDTO.getPassword())) {
				return userDetailsRepository.findByEmailAddress(userCredentials.getEmail());
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	public UserDetailsCollections createUser(UserDetailsDTO userDetailsDTO) {
		UserDetailsCollections userDetailsCollection = new UserDetailsCollections(userDetailsDTO.getFirstName(),
				userDetailsDTO.getLastName(), userDetailsDTO.getEmailAddress(), userDetailsDTO.getContactNumber(),
				userDetailsDTO.getRole());
		
		try {
			userDetailsCollection.setEmployeeID(String.valueOf(generateSequence(HOSTING_SEQ_KEY)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserCredentialsCollections userCredentialsCollections= new UserCredentialsCollections( userDetailsDTO.getEmailAddress(), userDetailsDTO.getPassword(),
				userDetailsDTO.getRole());
		userAuthenticationRepository.save(userCredentialsCollections);
		return userDetailsRepository.save(userDetailsCollection);

	}

	public long generateSequence(String key) throws Exception {
		// DatabaseSequence counter =
		// mongoOperations.findAndModify(query(Criteria.where("_id").is(seqName)),
		// new Update().inc("seq", 1),
		// Criteria.options().returnNew(true).upsert(true),
		// DatabaseSequence.class);
		// return !Objects.isNull(counter) ? counter.getSeq() : 1;

		// get sequence id
		Query query = new Query(Criteria.where("_id").is(key));

		// increase sequence id by 1
		Update update = new Update();
		update.inc("seq", 1);

		// return new increased id
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);

		// this is the magic happened.
		DatabaseSequence seqId = mongoOperation.findAndModify(query, update, options, DatabaseSequence.class);

		// if no id, throws SequenceException
		// optional, just a way to tell user when the sequence id is failed to
		// generate.
		if (seqId == null) {
			throw new Exception("Unable to get sequence id for key : " + key);
		}

		return seqId.getSeq();

	}

}
