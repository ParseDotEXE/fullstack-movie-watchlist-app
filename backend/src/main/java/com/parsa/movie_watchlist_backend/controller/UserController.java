package com.parsa.movie_watchlist_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:3000")
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
            else if (signupData.getPassword().length() < 6 || //must have at least 6 characters
                    !signupData.getPassword().matches(".*[!@#$%^&*].*") || // must have at least one special character
                    !signupData.getPassword().matches(".*[0-9].*") || // must have at least one number
                    !signupData.getPassword().matches(".*[A-Z].*") || // must have at least one uppercase letter
                    !signupData.getPassword().matches(".*[a-z].*")) { // must have at least one lowercase letter
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
                    
                    // return the created user
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
        if(userRepository.findByEmail(loginData.getEmail()).isPresent()){
            User user = userRepository.findByEmail(loginData.getEmail()).get(); // find the user by email
            
            //verify the password matches
            try{
                byte[] saltBytes = java.util.Base64.getDecoder().decode(user.getSalt()); //get the salt from the user object
                
                //check if the password matches the hashed password
                if(PasswordHasher.verifyPassword(loginData.getPassword(), user.getPassword(), saltBytes)){
                    // if password matches, retur the user (without password and salt)
                    
                    //return user
                    return user; // return the user object without password and salt
                
                }else{
                    // if password does not match, return an error
                    throw new RuntimeException("Invalid password");
                }
            } catch (Exception e) {
                throw new RuntimeException("Error verifying password: " + e.getMessage());
            }
        }else{
            // if user does not exist, return an error
            throw new RuntimeException("User not found");
        }
    }
}