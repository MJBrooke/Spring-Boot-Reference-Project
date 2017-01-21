package com.mike.service;

import com.mike.domain.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

//This is the updated version of SpringJUnit4ClassRunner in Boot 1.4.x
@RunWith(SpringRunner.class)
/*
Bootstrap the test class with the regular Boot magic of autowiring and bringing application.properties into the mix.
This brings the configuration for the @SpringBootApplication in from the main Spring class, meaning that it runs with the same configuration as when in operation.

Technically, we should be using @DataJpaTest for database tests, but this serves as a good example of testing service classes.
 */
@SpringBootTest
//Here, we tell the Spring Context to fire up with the listed set of active profiles, which impacts which beans are wired in depending on their configuration
@ActiveProfiles("DatabaseServices")
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void testGetBookList() {
        List<Book> bookList = bookService.getBookList();

        assertTrue(bookList.size() > 0);
    }

    @Test
    public void testCreateOrUpdateBook_addNewBook() {
        int numberOfEntriesInDatabaseBeforeInsertion = bookService.getBookList().size();

        Book bookToBeInsertedIntoDatabase = new Book(1, "Harry Potter and the Prisoner of Azkaban", 333, 4.1);
        Book bookFromDatabaseAfterInsertion = bookService.createOrUpdateBook(bookToBeInsertedIntoDatabase);

        int numberOfEntriesInDatabaseAfterInsertion = bookService.getBookList().size();

        assertTrue(bookFromDatabaseAfterInsertion.equals(bookToBeInsertedIntoDatabase));
        assertTrue(bookService.getBookById(bookFromDatabaseAfterInsertion.getId()).equals(bookToBeInsertedIntoDatabase));
        assertTrue(numberOfEntriesInDatabaseAfterInsertion == numberOfEntriesInDatabaseBeforeInsertion + 1);
    }

    @Test
    public void testGetBookById_updateExistingBook() {

        Book originalBook = bookService.getBookById(1);
        originalBook.setPageCount(1);

        bookService.createOrUpdateBook(originalBook);

        Book updatedBook = bookService.getBookById(1);
        assertTrue(updatedBook.getPageCount() == 1);
    }

    @Test
    public void testDeleteBook() {
        Book bookToBeDeleted = bookService.getBookById(1);

        bookService.deleteBook(bookToBeDeleted.getId());

        assertTrue(bookService.getBookById(1) == null);
    }

}
