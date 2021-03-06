package com.mike.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

/*
This is a simple POJO domain object, which can be used for both the front-end model, as well as a JPA entity if the program is small enough not need to separate those concerns.
 */
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    private String title;

    private Integer pageCount;

    private Double rating;

    public Book(){}

    public Book(Integer version, String title, Integer pageCount, Double rating) {
        this.version = version;
        this.title = title;
        this.pageCount = pageCount;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        if (pageCount != null ? !pageCount.equals(book.pageCount) : book.pageCount != null) return false;
        return rating != null ? rating.equals(book.rating) : book.rating == null;
    }
}
