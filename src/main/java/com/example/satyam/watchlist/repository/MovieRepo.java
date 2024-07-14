package com.example.satyam.watchlist.repository;

import com.example.satyam.watchlist.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Inmplementing JPA which provide different methods to apply CRUD operations
@Repository
public interface MovieRepo extends JpaRepository<Movie, Integer> {

}
