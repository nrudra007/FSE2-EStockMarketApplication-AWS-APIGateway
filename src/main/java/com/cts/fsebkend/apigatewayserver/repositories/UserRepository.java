package com.cts.fsebkend.apigatewayserver.repositories;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.cts.fsebkend.apigatewayserver.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@Repository
public class UserRepository {
	
	Logger log = LoggerFactory.getLogger(UserRepository.class);
	
	private static final String REDIS_INDEX_KEY = "E_STOCK_MARKET_USER_KEY";
	
	@Autowired
	RedisTemplate<String, User> redisTemplate;

	public List<User> findAll() {
		log.debug("*******within findAll..");
		List<User> allUsers = new ArrayList<User>();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Gson gson = new Gson();
			Map<Object, Object> entries = redisTemplate.opsForHash().entries(REDIS_INDEX_KEY);
			for(Entry<Object, Object> entry : entries.entrySet()){
				log.debug("*******user entry: "+entry.getValue());
		        String json = gson.toJson((LinkedHashMap)entry.getValue(), LinkedHashMap.class);
				log.debug("*******user JSON string: "+json);
				User user = objectMapper.readValue(json, User.class);
				allUsers.add(user);
			}
		}catch(Exception ex) {
			log.error("An exception occurred >> "+ex.getMessage());
		}
		
		return allUsers;
	}

	public User save(User newUser) {
		redisTemplate.opsForHash().put(REDIS_INDEX_KEY, newUser.getId(), newUser);
		log.debug("*******user registered successfully: "+newUser);
		ObjectMapper objectMapper = new ObjectMapper();
		Gson gson = new Gson();
		String json = gson.toJson((LinkedHashMap)(redisTemplate.opsForHash().get(REDIS_INDEX_KEY, newUser.getId())), LinkedHashMap.class);
		User registeredUser = null;
		try {
			registeredUser = objectMapper.readValue(json, User.class);
		} catch (JsonProcessingException ex) {
			log.error("An exception occurred >> "+ex.getMessage());
		}
		return registeredUser;
	}

	public void delete(User deletedUser) {
		redisTemplate.opsForHash().delete(REDIS_INDEX_KEY, deletedUser.getId());
	}
}
