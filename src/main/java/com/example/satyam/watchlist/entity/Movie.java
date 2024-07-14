package com.example.satyam.watchlist.entity;

import com.example.satyam.watchlist.entity.validations.Priority;
import com.example.satyam.watchlist.entity.validations.Rating;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//here using all the custum annotations we createdd
//@Entity used to marked class as java JPA - it allows to map object in to database
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "Oh Hey You Can't Leave it Blank")
    private String title;
    @Rating
    private float rating;
    @Priority //custom annotation
    private String priority;
    @Size(max = 50, message = "Do not over-express yourself, restrict to 50 words")
    private String comment;


//    defining all the getter and setter here
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
