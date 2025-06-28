# fullstack-movie-watchlist-app
A full-stack web application for managing your movie watchlist with intelligent prioritization.

## Tech Stack
- **Frontend:** React, Axios, Bootstrap/CSS
- **Backend:** Spring Boot, Spring Data JPA, PostgreSQL
- **External API:** The Movie Database (TMDB)

## Features
- Search for movies using TMDB API
- Add movies to personal watchlist
- Mark movies as watched and rate them
- View watchlist and watched movies

## Setup Instructions
[Coming soon]

## Project Structure
Entity Design:
- User Entity: id, username, email, password
- Movie Entity: Stores only movie references (tmdbId) and caches poster URL (posterUrl) for faster loading with TMDB API
- UserMovie Entity: userId, movieId, Movie "status" (0=watchlist, 1=watched), ratings are stored as "rating" field from 1-5 (increments of 0.5) as well as a dateAdded and dateWatched timestamps for easy sorting and tracking and future expansions - TODO: add personal notes section
JPA Entities:
