package com.cts.fsebkend.apigatewayserver.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.fsebkend.apigatewayserver.models.User;
import com.cts.fsebkend.apigatewayserver.repositories.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		User user = null;
		List<User> allUsers = findAllUsers();
		for(User selectedUser : allUsers) {
			if(userName.equalsIgnoreCase(selectedUser.getUserName())) {
				user = selectedUser;
				break;
			}
		}
		if (user == null) {
			throw new UsernameNotFoundException("User not found with userName: " + userName);
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				new ArrayList<>());
	}
	
	public User save(User user) {
		User newUser = new User();
		newUser.setId(user.getId());
		newUser.setUserName(user.getUserName());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setPanNo(user.getPanNo());
		return userRepository.save(newUser);
	}

	public void checkValidUser(String userName, String password) throws Exception {
		User user = null;
		List<User> allUsers = findAllUsers();
		for(User selectedUser : allUsers) {
			if(userName.equalsIgnoreCase(selectedUser.getUserName())) {
				user = selectedUser;
				break;
			}
		}
		if (user == null) {
			throw new Exception("User not found with userName: " + userName);
		}
		if(!BCrypt.checkpw(password, user.getPassword())) {
			throw new Exception("Incorrect User Name and/or Password!!");
		}
	}

	public boolean ifUserExists(User user) {

		boolean isUserExists = false;
		List<User> allUsers = findAllUsers();
		for(User selectedUser : allUsers) {
			if(user.getPanNo().equalsIgnoreCase(selectedUser.getPanNo())) {
				isUserExists = true;
				break;
			}
		}
		return isUserExists;
	}

	public void deleteUserByUserName(String userName) {

		List<User> allUsers = findAllUsers();
		for(User deletedUser : allUsers) {
			if(userName.equalsIgnoreCase(deletedUser.getUserName())) {
				userRepository.delete(deletedUser);
				break;
			}
		}
	}
}
