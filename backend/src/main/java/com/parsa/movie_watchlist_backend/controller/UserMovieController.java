package com.parsa.movie_watchlist_backend.controller;

import java.time.LocalDateTime;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parsa.movie_watchlist_backend.repository.MovieRepository;
import com.parsa.movie_watchlist_backend.repository.UserMovieRepository;
import com.parsa.movie_watchlist_backend.repository.UserRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.parsa.movie_watchlist_backend.dto.WatchedRequest;
import com.parsa.movie_watchlist_backend.entity.Movie;
import com.parsa.movie_watchlist_backend.entity.User;
import com.parsa.movie_watchlist_backend.entity.UserMovie;
import com.parsa.movie_watchlist_backend.entity.UserMovieId;




//handles watchlist operations
@RestController
@RequestMapping("/api/users")
public class UserMovieController {
    @Autowired
    private UserMovieRepository userMovieRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository; //TODO -> done
    //methods
    //add to watchlist
    @PostMapping("/{userId}/watchlist")
    public UserMovie addToWatchlist(@PathVariable Long userId, @RequestBody Movie movie) {
        //save movie to database if it doesn't exist
        if(movieRepository.findById(movie.getTmdbId()).isEmpty()) {
            //add it to the database
            movieRepository.save(movie);
        }
        //create UserMovie entity to link user and current movie
        UserMovie entity = new UserMovie();
        //user must exist in database before adding to watchlist - find them
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            //handle case where user doesn't exist
            throw new RuntimeException("User not found");
        }
        entity.setUser(user); //set user object -> it has the user id with it
        entity.setUserId(user.getId()); //set user id
        entity.setMovie(movie); //set movie object
        entity.setMovieId(movie.getTmdbId()); //set movie id
        entity.setStatus(0); //0 for watchlist
        entity.setDateAdded(LocalDateTime.now()); //added now

        //save the userMovie entity
        userMovieRepository.save(entity);
        //return the entity
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
    public UserMovie markAsWatched(@PathVariable Long userId, @PathVariable Integer movieId, @RequestBody WatchedRequest request) {
        //find the UserMovie entity with composite key
        UserMovieId userMovieId = new UserMovieId(userId, movieId);
        Optional<UserMovie> userMovie = userMovieRepository.findByUserIdAndMovieId(userId, movieId);
        //now update the status, rating, and dateWatched of the movie
        UserMovie entity = userMovie.get();
        entity.setStatus(1); //1 for watched
        entity.setRating(request.getRating()); //set rating from request
        entity.setDateWatched(LocalDateTime.now()); //set date watched to now
        //save the updated entity
        userMovieRepository.save(entity);
        //return the updated entity
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
