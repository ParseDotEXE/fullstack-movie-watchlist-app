package com.parsa.movie_watchlist_backend.dto;

public class WatchedRequest {
    private Integer rating;
    
    public WatchedRequest() {
    }
    //gettter and setter for rating
    public Integer getRating(){
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
