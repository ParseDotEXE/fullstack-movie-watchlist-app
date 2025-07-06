package com.parsa.movie_watchlist_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parsa.movie_watchlist_backend.dto.AuthenticationRequest;
import com.parsa.movie_watchlist_backend.dto.SignupRequest;
import com.parsa.movie_watchlist_backend.entity.User;
import com.parsa.movie_watchlist_backend.repository.UserRepository;

//handles user-related endpoints
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository; //repository to access user data

    // authenticate the user
   @PostMapping("/signup")
   public User signup(@RequestBody SignupRequest signupData) {
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

       
       return null;
   }

    // log in the user by email and password
   @PostMapping("/login")
   public User login(@RequestBody AuthenticationRequest loginData) {
         //TODO:
         //find the user by email in database
         //check if the user already exists
         //verify password matches
         //return user if valid (without password)
         //return error if user does not exist or password is incorrect
         //edge cases:
         //user not found
         //password does not match

         return null; // Temporary return to satisfy compiler
   }
    
}
