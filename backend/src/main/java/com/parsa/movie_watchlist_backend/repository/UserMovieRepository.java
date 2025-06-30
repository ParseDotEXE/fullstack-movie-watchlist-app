package com.parsa.movie_watchlist_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parsa.movie_watchlist_backend.entity.UserMovie;
import com.parsa.movie_watchlist_backend.entity.UserMovieId;
import java.util.List;  // for return types of custom methods
import java.util.Optional;

//interface used for cleaner code and automatically generated methods
public interface UserMovieRepository extends JpaRepository<UserMovie, UserMovieId> {
    //custom method to get list of watched or want to watch movies
    List<UserMovie> findByUserIdAndStatus(Long userId, Integer status);
    // custom method to find a UserMovie by userId and movieId
    Optional<UserMovie> findByUserIdAndMovieId(Long userId, Integer movieId);
}
