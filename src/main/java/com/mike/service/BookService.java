package com.mike.service;

import com.mike.domain.Book;

import java.util.List;

/*
It is always a good idea to code to interfaces in Spring.
It allows for different implementations to be wired into classes (depending on the context or active profiles) without changing any Java code.
Note that the interface itself is not annotated, as it is not a component - the impl versions, however, will be annotated with @Service
 */
public interface BookService {

    List<Book> getBookList();

    Book createOrUpdateBook(Book book);

    Book getBookById(Integer id);

    void deleteBook(Integer id);
}
