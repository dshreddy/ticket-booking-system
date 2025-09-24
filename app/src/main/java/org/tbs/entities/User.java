package org.tbs.entities;

import java.util.List;

public class User {
    private String name;
    private String hashedPassword;
    private String userId;
    private List<String> ticketsBooked;

    public User(String name, String hashedPassword, String userId, List<String> ticketsBooked) {
        this.name = name;
        this.hashedPassword = hashedPassword;
        this.userId = userId;
        this.ticketsBooked = ticketsBooked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getTicketsBooked() {
        return ticketsBooked;
    }

    public void setTicketsBooked(List<String> ticketsBooked) {
        this.ticketsBooked = ticketsBooked;
    }
}
