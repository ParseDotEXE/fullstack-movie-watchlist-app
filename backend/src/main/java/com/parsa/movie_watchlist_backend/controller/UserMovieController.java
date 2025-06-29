package com.parsa.movie_watchlist_backend.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.parsa.movie_watchlist_backend.repository.UserMovieRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.parsa.movie_watchlist_backend.entity.Movie;
import com.parsa.movie_watchlist_backend.entity.UserMovie;




//handles watchlist operations
@RestController
@RequestMapping("/api/users")
public class UserMovieController {
    @Autowired
    private UserMovieRepository userMovieRepository;

    //methods
    //add to watchlist
    @PostMapping("/{userId}/watchlist")
    public UserMovie addToWatchlist(@PathVariable Long userId, @RequestBody Movie movie) {
        UserMovie entity = new UserMovie();
        
        
        return entity;
    }
    //get watchlist
    @GetMapping("/{userId}/watchlist")
    public List<UserMovie> getWatchlist(@PathVariable Long userId) {
        List<UserMovie> watchlist = userMovieRepository.findByUserIdAndStatus(userId, 0);
        return watchlist;
    }
    //get watched movies
    @GetMapping("/{userId}/watched")
    public List<UserMovie> getWatchedlist(@PathVariable Long userId) {
        List<UserMovie> watchedlist = userMovieRepository.findByUserIdAndStatus(userId, 1);
        return watchedlist;
    }
    //mark as watched
    @PutMapping("/{userId}/movies/{movieId}/watched")
    public String putMethodName(@PathVariable String id, @RequestBody String entity) {
        //TODO: process PUT request
        
        return entity;
    }
    //update rating
    @PutMapping("/{userId}/movies/{movieId}/rating")
    public String putMethodName(@PathVariable String id, @RequestBody String entity) {
        //TODO: process PUT request
        
        return entity;
    }
    //delete movie
    @DeleteMapping("/{userId}/movies/{movieId}")
    
    
    
}
