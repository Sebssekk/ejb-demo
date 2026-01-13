package seb.course.rest;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.json.bind.annotation.JsonbNillable;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTransient;

import java.time.LocalDate;

public class UserPost {
    @JsonbProperty("username")
    private String author;
    private String title;
    private String body;
    @JsonbNillable
    private float stars = 5;
    @JsonbTransient
    private String userSecret;
    @JsonbDateFormat("yyyy-LL-dd")
    private LocalDate publishDate;

    public UserPost() {
    }

    public boolean isValid(){
        if (author == null || author.isEmpty())
            return false;
        if (title == null || title.isEmpty())
            return false;
        if (body == null || body.isEmpty())
            return false;
        if (stars < 0 || stars > 5)
            return false;
        return true;
    }
    public String getUserSecret() {
        return userSecret;
    }

    public void setUserSecret(String userSecret) {
        this.userSecret = userSecret;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }
}
