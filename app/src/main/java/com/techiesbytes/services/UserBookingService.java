package com.techiesbytes.services;

import com.techiesbytes.entities.User;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserBookingService {
	private User user;
	
	private List<User> userList;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	private static final String USERS_PATH = "../localDb/users.json";
	
	public UserBookingService(User user) throws IOException{
		this.user = user;
		
		File users = new File(USERS_PATH);
		
		userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
	}
	
    public Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user -> {
            return user.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user1){
        try{
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }
}