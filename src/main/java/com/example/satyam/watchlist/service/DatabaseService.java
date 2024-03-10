package com.example.satyam.watchlist.service;

import com.example.satyam.watchlist.entity.Movie;
import com.example.satyam.watchlist.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseService {
    @Autowired
    MovieRepo movieRepo;

    @Autowired
    RatingService ratingService;

    public void create(Movie movie){
        String rating = ratingService.getMovieRating(movie.getTitle());
        if(rating != null){
            movie.setRating(Float.parseFloat(rating));
        }
        movieRepo.save(movie);
    }

    public List<Movie> getAllMovies(){
        //will return movie list
        return movieRepo.findAll();
    }

    public Movie getMovieById(Integer id){
        return movieRepo.findById(id).get();
    }

    public void update(Movie movie, Integer id){
        Movie toBeUpdated = getMovieById(id);
        toBeUpdated.setTitle(movie.getTitle());
        toBeUpdated.setComment(movie.getComment());
        toBeUpdated.setRating(movie.getRating());
        toBeUpdated.setPriority(movie.getPriority());

        movieRepo.save(toBeUpdated);
    }
}
