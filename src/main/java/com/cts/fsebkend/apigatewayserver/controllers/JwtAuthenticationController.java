package com.cts.fsebkend.apigatewayserver.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.fsebkend.apigatewayserver.configs.JwtTokenUtil;
import com.cts.fsebkend.apigatewayserver.models.JwtRequest;
import com.cts.fsebkend.apigatewayserver.models.JwtResponse;
import com.cts.fsebkend.apigatewayserver.models.User;
import com.cts.fsebkend.apigatewayserver.services.JwtUserDetailsService;

@RestController
@RequestMapping("/e-stock-market/api/v1.0/market/user")
public class JwtAuthenticationController {

	Logger log = LoggerFactory.getLogger(JwtAuthenticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@GetMapping("/list")
	public ResponseEntity<?> healthCheck() {
		return new ResponseEntity<>(userDetailsService.findAllUsers(), HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		if(userDetailsService.ifUserExists(user)) {
			return new ResponseEntity<>("User with pan no.: "+user.getPanNo()
			+" already exists, Please login instead of registration..", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(userDetailsService.save(user), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		try {
			userDetailsService.checkValidUser(authenticationRequest.getUserName(), authenticationRequest.getPassword());
		}catch(Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
		}

		authenticate(authenticationRequest.getUserName(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUserName());

		final String token = jwtTokenUtil.generateToken(userDetails);
		return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
	}

	@GetMapping("/delete/{username}")
	public ResponseEntity<String> deleteUserByUserName(@PathVariable("username") String userName) {
		log.debug("within deleteUserByUserName..");
		ResponseEntity<String> resp = null;
		try {
			userDetailsService.deleteUserByUserName(userName);
			resp = new ResponseEntity<>("User having mentioned userName have successfully deleted..", HttpStatus.OK);
		}catch(Exception ex) {
			log.error("An exception occurred while deleting the user by userName!!");
			resp = new ResponseEntity<>("Unable to delete user having mentioned userName!!", HttpStatus.BAD_GATEWAY);
		}
		return resp;
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
