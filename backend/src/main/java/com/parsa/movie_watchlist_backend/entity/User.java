package com.parsa.movie_watchlist_backend.entity;

import jakarta.persistence.*; //basic JPA annotations
import org.hibernate.annotations.CreationTimestamp; //for timestamping
import org.hibernate.annotations.UpdateTimestamp; //for timestamping
import java.time.LocalDateTime; //for timestamping
import java.util.Objects;  

@Entity //indicates that this class is a JPA entity
public class User {
    @Id //indicates that this field is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-generate the ID
    private Long id;
    private String username;
    private String email;
    private String password;

    //TODO: constructors, getters, setters -> done
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public User() {
    }
    //getters
    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    //setters
    public void setId(Long id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
