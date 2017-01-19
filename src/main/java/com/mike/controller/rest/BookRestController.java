package com.mike.controller.rest;

import com.mike.domain.Book;
import com.mike.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
Here is a simple example of a REST controller, which exposes its contents as JSON when its endpoint is called
 */

@RestController
@RequestMapping("/rest/book")
public class BookRestController {

    //How to get an instance of the logger for proper logging
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BookService bookService;

    @RequestMapping("/{id}")
    public Book getBook(@PathVariable Integer id) {
        return bookService.getBookById(id);
    }

    @RequestMapping("/list")
    public List<Book> getBookList() {

        //Example of the different levels of logging, which can be turned on or off in the application.properties file.
        logger.info("[INFO] getBook() REST service hit");
        logger.debug("[DEBUG] getBook() REST service hit");
        logger.error("[ERROR] getBook() REST service hit");

        return bookService.getBookList();
    }
}
