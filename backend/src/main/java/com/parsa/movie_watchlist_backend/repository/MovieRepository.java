package com.parsa.movie_watchlist_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.parsa.movie_watchlist_backend.entity.Movie;
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    //need to find movie in database -> findById(Integer movie)
    //need to save movie in database -> save(Movie movie);
    //need to check if movie exists in database -> use find and if not then save
}
