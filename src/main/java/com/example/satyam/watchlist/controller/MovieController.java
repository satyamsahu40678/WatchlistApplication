package com.example.satyam.watchlist.controller;

import com.example.satyam.watchlist.entity.Movie;
import com.example.satyam.watchlist.service.DatabaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MovieController {

    @Autowired
    DatabaseService databaseService;
    @GetMapping("/watchlistItemForm")
    public ModelAndView showWatchListForm(@RequestParam(required = false) Integer id){ //we are keeping request require false so it is only needed when we click on update
        System.out.println(id);
        String viewName = "watchlistItemForm";

        Map<String, Object> model = new HashMap<>();


        if(id == null){
            model.put("watchlistItem", new Movie());
        }
        else{
            model.put("watchlistItem", databaseService.getMovieById(id));
        }

//        Movie dummyMovie = new Movie();
//        dummyMovie.setTitle("dummy");
//        dummyMovie.setRating(0);
//        dummyMovie.setPriority("Low");
//        dummyMovie.setComment("Jhon doe");

//        model.put("watchlistItem", dummyMovie);


        return new ModelAndView(viewName, model);
    }

    @PostMapping("/watchlistItemForm")
    public ModelAndView submitWatchListForm(@Valid @ModelAttribute("watchlistItem") Movie movie, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            //if errors are there, redisplay the form and let user enter again
            return new ModelAndView("watchlistItemForm");
        }
        /* logic when to create new movie and when to update
        * if(id == null){
        * create new movie
        * }
        * else{
        * update
        * }
        * */
        Integer id = movie.getId();
        if(id == null){
            databaseService.create(movie);
        }
        else{
            databaseService.update(movie, id);
        }
        databaseService.create(movie);
        //will redirect to watchlist page
        RedirectView rd = new RedirectView();
        rd.setUrl("/watchlist");

        //sending my whole redirect view
        return new ModelAndView(rd);
    }

    @GetMapping("/watchlist")
    public ModelAndView getWatchlist(){
        //will get the form at watchlistItemForm as redirecting after submitting movie
        String viewName = "watchlist";
        Map<String, Object> model = new HashMap<>();

        List<Movie> movieList = databaseService.getAllMovies();
        model.put("watchlistrows", movieList);
        model.put("noofmovies", movieList.size());
        return new ModelAndView(viewName, model);
    }
}
