package com.parsa.movie_watchlist_backend.entity;

import java.util.Objects;
import java.io.Serializable;

public class UserMovieId implements Serializable {
    private Long userId;
    private Integer movieId;

    // TODO: default constructor, pram constructor, equals() and hashCode() method,
    // getters/setters
    // default const for JPA
    public UserMovieId() {
    }

    // param const
    public UserMovieId(Long userId, Integer movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }

    // getters
    public Long getUserId() {
        return userId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    // setters
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }
    //equals method
    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if(o == null || o.getClass() != this.getClass()){
            return false;
        }else{
            //typecast
            UserMovieId that = (UserMovieId) o;
            //check if equal
            return Objects.equals(userId, that.userId) && Objects.equals(movieId, that.movieId); 
        }
    }
    //hashCode methd
    @Override
    public int hashCode(){
        return Objects.hash(userId, movieId); //these two methods are to look up UserMovieId
    }
}
