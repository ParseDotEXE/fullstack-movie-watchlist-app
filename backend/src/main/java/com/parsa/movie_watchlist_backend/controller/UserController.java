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
       //TODO:
       //validate input data to see if email and password already exist
       //create a new User object from SignupRequest data
       //save user to database
       //return the created user (without password)
       //edge cases:
         // - if email already exists, return an error
         // - if password is too short and or does not meet criteria, return an error
         //if username is already taken, return an error
         //if there are empty fields, return an error

       
       return entity;
   }

    // log in the user by email and password
   @PostMapping("/api/auth/login")
   public User login(@RequestBody loginData) {
         //TODO:
         //find the user by email in database
         //check if the user already exists
         //verify password matches
         //return user if valid (without password)
         //return error if user does not exist or password is incorrect
         //edge cases:
         //user not found
         //password does not match
   }
    
}
