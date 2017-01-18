package com.mike.bootstrap;

import com.mike.domain.Book;
import com.mike.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DatabaseBookBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private BookService bookService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadInitialSetOfBooksIntoDatabase();
    }

    private void loadInitialSetOfBooksIntoDatabase() {
        bookService.createOrUpdateBook(new Book(1, "Harry Potter and the Philosopher's Stone", 123, 4.5));
        bookService.createOrUpdateBook(new Book(2, "Harry Potter and the Chamber of Secrets", 254, 4.7));
    }
}
