package com.parsa.movie_watchlist_backend.dto;

public class AuthenticationRequest {
    private String email;
    private String password;
    //detault constructor
    public AuthenticationRequest() {
    }
    //getter and setter methods
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
