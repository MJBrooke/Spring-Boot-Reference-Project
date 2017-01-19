package com.mike.bootstrap;

import com.mike.domain.Book;
import com.mike.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/*
This component catches the startup of the Spring Context.
We can use this hook to load up runtime dev database with data through the use of our services.

An advantage of this approach is that the component can be profiled easily, as it is simply a bean.
The disadvantage is that we are not using native SQL, which makes this a little less maintainable with changes.
 */

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
