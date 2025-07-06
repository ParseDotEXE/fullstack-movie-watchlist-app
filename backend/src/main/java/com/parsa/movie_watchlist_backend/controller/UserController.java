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
import com.parsa.movie_watchlist_backend.util.PasswordHasher;

//handles user-related endpoints
@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UserRepository userRepository; // repository to access user data

    // authenticate the user
    @PostMapping("/signup")
    public User signup(@RequestBody SignupRequest signupData) {
        // TODO:
        // validate input data to see if email and password already exist
        if (userRepository.findByEmail(signupData.getEmail()).isEmpty()) {
            // if email does not exist, make the account
            
            if (userRepository.existsByUsername(signupData.getUsername())) {
                // if the username is already taken return an error
                throw new RuntimeException("Username already taken");
            }
            //check if the password meets the criteria 
            else if (signupData.getPassword().length() < 6 ||
                    !signupData.getPassword().matches(".*[!@#$%^&*].*") ||
                    !signupData.getPassword().matches(".*[0-9].*") ||
                    !signupData.getPassword().matches(".*[A-Z].*") ||
                    !signupData.getPassword().matches(".*[a-z].*")) {
                throw new RuntimeException(
                        "Password must be at least 6 characters with special char, number, uppercase, and lowercase letter");
            } else {
                // create a new User object from SignupRequest data
                User newUser = new User();
                newUser.setEmail(signupData.getEmail()); // set the email from signup data
                newUser.setUsername(signupData.getUsername()); // set the username from signup data
                
                // hash the password before saving it in production
                byte[] saltBytes = PasswordHasher.generateSalt(); // generate a random salt
                String salt = java.util.Base64.getEncoder().encodeToString(saltBytes); // encode salt to string
                
                newUser.setSalt(salt); //set the salt for password hashing
                
                //use the PasswordHasher utility to hash the password with the salt
                try {
                    String hashedPassword = PasswordHasher.hashPassword(signupData.getPassword(), saltBytes);
                    
                    newUser.setPassword(hashedPassword); // set the hashed password

                    //save the user to the databse
                    User savedUser = userRepository.save(newUser);
                    
                    // return the created user (without password)
                    savedUser.setPassword(null); // remove password from the returned user object
                    savedUser.setSalt(null); // remove salt from the returned user object
                    return savedUser;
                
                }catch (Exception e) {
                    throw new RuntimeException("Error creating user: " + e.getMessage());
                }
            }
        }else{
            // if email already exists, return an error
            throw new RuntimeException("Email already exists");
        }
        // create a new User object from SignupRequest data
        // save user to database
        // return the created user (without password)
        // edge cases:
        // - if email already exists, return an error
        // - if password is too short and or does not meet criteria, return an error
        // if username is already taken, return an error
        // if there are empty fields, return an error
    }

    // log in the user by email and password
    @PostMapping("/login")
    public User login(@RequestBody AuthenticationRequest loginData) {
        // TODO:
        // find the user by email in database
        // check if the user already exists
        // verify password matches
        // return user if valid (without password)
        // return error if user does not exist or password is incorrect
        // edge cases:
        // user not found
        // password does not match

        return null; // Temporary return to satisfy compiler
    }

}
