package com.example.satyam.watchlist.service;

import com.example.satyam.watchlist.entity.Movie;
import com.example.satyam.watchlist.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//this is spring service class
@Service
public class DatabaseService {
    @Autowired
    MovieRepo movieRepo;

    @Autowired
    RatingService ratingService;

    //craeting movie list i mean adding movie and saving movie to database
    public void create(Movie movie){
        String rating = ratingService.getMovieRating(movie.getTitle());
        if(rating != null){
            movie.setRating(Float.parseFloat(rating));
        }
        movieRepo.save(movie);
    }

//    this will retrieve all the movies
    public List<Movie> getAllMovies(){
        //will return movie list
        return movieRepo.findAll();
    }


//    to get the movie id else
    public Movie getMovieById(Integer id){
        return movieRepo.findById(id).get();
    }


//    function to update movie in movielist
    public void update(Movie movie, Integer id){
        Movie toBeUpdated = getMovieById(id);
        toBeUpdated.setTitle(movie.getTitle());
        toBeUpdated.setComment(movie.getComment());
        toBeUpdated.setRating(movie.getRating());
        toBeUpdated.setPriority(movie.getPriority());

        movieRepo.save(toBeUpdated);
    }
}
