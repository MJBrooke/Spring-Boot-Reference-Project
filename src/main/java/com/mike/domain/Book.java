package com.mike.domain;

/*
This is a simple POJO domain object, which can be used for both the front-end model, as well as a JPA entity if the program is small enough not need to separate those concerns.
 */
public class Book {

    private Integer id;

    private String title;

    private Integer pageCount;

    private Double rating;

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

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
