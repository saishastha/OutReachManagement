package com.outreach.management.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.outreach.management.collections.UserDetailsCollections;
import com.outreach.management.dto.UserCredentialsDTO;
import com.outreach.management.dto.UserDetailsDTO;
import com.outreach.management.services.UserAuthenticationService;

//import io.micrometer.core.instrument.util.StringUtils;

@RestController
@RequestMapping("/user")
public class UserAuthenticationController {

	@Autowired
	private UserAuthenticationService userAuthenticationService;

	@PostMapping("/login")
	public ResponseEntity<UserDetailsCollections> authenticateUser(@RequestBody UserCredentialsDTO userCredentialsDTO) {
		UserDetailsCollections userDetails = userAuthenticationService.authenticateUser(userCredentialsDTO);
		return Objects.nonNull(userDetails) ? new ResponseEntity<>(userDetails, HttpStatus.OK)
				: new ResponseEntity<>(userDetails, HttpStatus.UNAUTHORIZED);
	}

	@PostMapping("/signup")
	public ResponseEntity<UserDetailsCollections> createUser(@RequestBody UserDetailsDTO userDetailsDTO) {
		UserDetailsCollections userDetailsCollections = userAuthenticationService.createUser(userDetailsDTO);
		return Objects.nonNull(userDetailsCollections)
				? new ResponseEntity<>(userDetailsCollections, HttpStatus.CREATED)
				: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
