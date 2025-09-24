package org.tbs.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.tbs.entities.User;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class UserBookingService {
    // Users DB (in RAM)
    private List<User> userList;

    // Users DB (in disk)
    private static final String USERS_FILE_PATH = "app/src/main/java/org/tbs/db/users.json";

    // Jackson Object Mapper to load from and store into JSON files
    private final ObjectMapper objectMapper = new ObjectMapper();

    public UserBookingService() {
        try {
            File users = new File(USERS_FILE_PATH);
            userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
        } catch (Exception ex) {
            System.out.println("Failed to load the users list from json file : " + ex);
        }
    }

    // GET API
    public User login(String username, String hashedPassword) {
        for(User user : userList) {
            if(username.equals(user.getName()) && hashedPassword.equals(user.getHashedPassword())) {
                return user;
            }
        }
        return null;
    }

    // POST API
    public User signup(String username, String hashedPassword) {
        for(User user : userList) {
            if(username.equals(user.getName()) && hashedPassword.equals(user.getHashedPassword())) {
                return null;
            }
        }
        User newUser = new User(username, hashedPassword, ((Integer) userList.size()).toString(), Collections.emptyList());
        userList.add(newUser);
        saveUserListToFile();
        return newUser;
    }

    private void saveUserListToFile() {
        try {
            objectMapper.writeValueAsBytes(userList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
