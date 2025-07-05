package com.parsa.movie_watchlist_backend.dto;

public class SignupRequest {
    private String email;
    private String username;
    private String password;
    //default constructor
    public SignupRequest(){
    }
    //getter and setter methods
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
