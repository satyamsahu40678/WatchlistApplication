package com.example.satyam.watchlist.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RatingService {
    String apiUrl = "http://www.omdbapi.com/?i=tt3896198&apikey=73019a5d&t=";

    public String getMovieRating(String title){
        try{
            //try to fetch the rating by calling omdb api
            RestTemplate template = new RestTemplate();
            //apiurl + title
            ResponseEntity<ObjectNode> response = template.getForEntity(apiUrl + title, ObjectNode.class);
            ObjectNode jsonObject = response.getBody(); //it will get the complete json
            System.out.println(jsonObject.path("imdbRating").asText());
            return jsonObject.path("imdbRating").asText();
        }
        catch (Exception e){
            //to user enetered rating will be taken
            System.out.println("Ya to movie available nhi ha ya fir api ki watt lg gyi hai");
            return null;
        }
    }
}
