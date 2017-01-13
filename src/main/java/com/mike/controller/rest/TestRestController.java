package com.mike.controller.rest;

import com.mike.domain.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
Here is a simple example of a REST controller, which exposes its contents as JSON when its endpoint is called
 */

@RestController
@RequestMapping("/rest")
public class TestRestController {

    //How to get an instance of the logger for proper logging
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/book")
    public Book getBook() {

        //Example of the different levels of logging, which can be turned on or off in the application.properties file.
        logger.info("[INFO] getBook() REST service hit");
        logger.debug("[DEBUG] getBook() REST service hit");
        logger.error("[ERROR] getBook() REST service hit");

        Book book = new Book();
        book.setId(1);
        book.setPageCount(304);
        book.setTitle("Harry Potter");
        book.setRating(4.78);

        return book;
    }
}
