package com.parsa.movie_watchlist_backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.parsa.movie_watchlist_backend.entity.User;
import java.util.*;

public interface UserRepository extends JpaRepository<User, Long>{
    //nned to find user in database -> findByEmail
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    //need to save user in database -> save(User user);
    //use the built in User save(User user);
}
