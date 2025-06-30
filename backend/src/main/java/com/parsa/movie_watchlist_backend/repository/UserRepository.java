package com.parsa.movie_watchlist_backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.parsa.movie_watchlist_backend.entity.User;
public interface UserRepository extends JpaRepository<User, Long>{
    //nned to find user in database -> findById(Long userId)
    //need to save user in database -> save(User user);
    //need to save their email, username, and password
}
