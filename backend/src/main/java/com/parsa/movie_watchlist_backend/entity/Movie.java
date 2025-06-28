package com.parsa.movie_watchlist_backend.entity;

import jakarta.persistence.*; //basic JPA annotations

@Entity
public class Movie {
    @Id // indicates that this field is the primary key
    private Integer tmdbId;
    private String posterUrl;
    private String title;

    // constructor

    // empty one for JPA
    public Movie() {

    }

    // one with parameters for TMDB API
    public Movie(Integer tmdbId, String title, String posterUrl) {
        this.tmdbId = tmdbId;
        this.title = title;
        this.posterUrl = posterUrl;
    }

    // one for potentially only creating a movie with just an id
    public Movie(Integer tmdbId) {
        this.tmdbId = tmdbId;
    }

    // getters
    public Integer getTmdbId() {
        return tmdbId;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    // setters
    public void setTmdbId(Integer tmdbId) {
        this.tmdbId = tmdbId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
}
