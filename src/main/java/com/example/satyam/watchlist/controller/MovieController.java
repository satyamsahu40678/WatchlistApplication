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

//@RestController annotation is a combination of @Controller and @ResponseBody.
//It indicates that this class handles HTTP requests and returns the response body
@RestController
public class MovieController {

    @Autowired
    DatabaseService databaseService;

    //this method mapping get request to /watchlistItemForm...here we are handling the adding and updating movie
    //it will show our watchlist
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
        return new ModelAndView(viewName, model);
    }


    //handling post request Binding Result holds the result of validation
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


    //It retrieves all teh m0vies list from the database and display them on wtachlist page all we add movies list here
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
