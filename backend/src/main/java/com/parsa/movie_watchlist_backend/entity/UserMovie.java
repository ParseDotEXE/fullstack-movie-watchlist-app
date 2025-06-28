package com.parsa.movie_watchlist_backend.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@IdClass(UserMovieId.class) // tells JPA this entity uses a composite key and key structure is defined in
                            // userMovieId.class
public class UserMovie {
    @Id
    private Long userId;
    @Id
    Integer movieId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    Movie movie;
    Integer status;
    Integer rating;
    LocalDateTime dateAdded;
    LocalDateTime dateWatched;

    // constructors

    // default
    public UserMovie() {
    }

    // with param
    public UserMovie(User user, Movie movie) {
        this.movie = movie;
        this.user = user;
        this.movieId = movie.getTmdbId();
        this.userId = user.getId();
        this.dateAdded = LocalDateTime.now();
        this.status = 0;
    }

    // constructor for watched movies
    public UserMovie(Movie movie, Integer rating) {
        this.movie = movie;
        this.status = 1;
        this.rating = rating;
    }

    //getters
    public Long getUserId() {
        return userId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public User getUser() {
        return user;
    }

    public Movie getMovie() {
        return movie;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getRating() {
        return rating;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public LocalDateTime getDateWatched() {
        return dateWatched;
    }

    //setters
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setDateWatched(LocalDateTime dateWatched) {
        this.dateWatched = dateWatched;
    }
}
