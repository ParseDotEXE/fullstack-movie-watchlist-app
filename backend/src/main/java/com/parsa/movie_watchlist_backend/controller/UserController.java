package com.parsa.movie_watchlist_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.parsa.movie_watchlist_backend.entity.User;

//handles user-related endpoints
@Controller
public class UserController {
    // authenticate the user
   @PostMapping("/api/auth/login")
   public User signup(@RequestBody  signupData) {
       //TODO: process POST request
       
       return entity;
   }

    // log in the user by email and password
   @PostMapping("/api/auth/login")
   public User login(@RequestBody loginData) {
    
   }
    
}
