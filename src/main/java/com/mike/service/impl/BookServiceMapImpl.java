package com.mike.service.impl;

import com.mike.domain.Book;
import com.mike.service.BookService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
An example implementation of the BookService interface.
This one fulfils the service requirements by creating them with Java code - useful when you have not yet setup, or do not want to use, a database whilst developing.
In this service, we are implementing CRUD operations.
 */

@Service
@Profile("MapServices")
public class BookServiceMapImpl implements BookService {

    private Map<Integer, Book> books = new HashMap<>();
    private Integer currentSequenceIdValue = 0;

    public BookServiceMapImpl() {
        loadInitialBookSet();
    }

    @Override
    public List<Book> getBookList() {
        return new ArrayList<>(books.values());
    }

    @Override
    public Book createOrUpdateBook(Book book) {

        if(book.getId() == null) {
            addBookToMap(book);
        } else {
            updateBookInMap(book);
        }

        return book;
    }

    @Override
    public void deleteBook(Integer id) {
        books.remove(id);
    }

    @Override
    public Book getBookById(Integer id) {
        return books.get(id);
    }

    private void loadInitialBookSet() {
        Book book = new Book();
        book.setId(1);
        book.setPageCount(304);
        book.setTitle("Harry Potter and the Philosopher's stone");
        book.setRating(4.78);

        Book book2 = new Book();
        book2.setId(2);
        book2.setPageCount(506);
        book2.setTitle("Harry Potter and the Chamber of Secrets");
        book2.setRating(3.9);

        addBookToMap(book);
        addBookToMap(book2);
    }

    private void addBookToMap(Book book) {

        book.setId(currentSequenceIdValue);
        books.put(currentSequenceIdValue, book);
        currentSequenceIdValue++;
    }

    private void updateBookInMap(Book book) {
        books.put(book.getId(), book);
    }
}
