package com.mike.controller.view;

import com.mike.domain.Book;
import com.mike.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/*
An example of a Spring MVC unit test, using the Spring web testing framework.
 */
public class BookControllerTest {

    private MockMvc mockMvc;

    /*
    Here, we declare each of the services that are declared inside of the controller as mocks
     */
    @Mock
    private BookService bookService;

    /*
    We then mark the controller to be injected using each of the previously declared mocks, instead of the usual autowiring.
     */
    @InjectMocks
    private BookController bookController;

    /*
    This method is run before each method marked with the @Test annotation.
    This ensure that the controller and the mocked services are all freshly created before each test to prevent leakage between unit tests.
     */
    @Before
    public void before() {
        //Create the mocks for the services stipulated in this test class
        MockitoAnnotations.initMocks(this);

        /*
        Build the MVC controller for use in the tests.
        Note that this is beneficial, because it only creates the controller bean, which mitigates the need to bring up the entire Spring context.
        This allows the test to run much faster than if an entire context had to be created.
         */
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void testGetBookList() throws Exception {

        /*
        Create a mock set of books to be returned by the service.
        Remember that the service should have its own test class - we need not test it again here, which is why we mock its response
         */
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book());
        bookList.add(new Book());

        //Specify what should be returned when this service call is hit in the controller (since the service is mocked)
        when(bookService.getBookList()).thenReturn(bookList);

        /*
        Mock perform the set of steps that would be natively executed by the browser.
        You then have access to the response that Spring would create, including the status, view name and model.
        You can make necessary assertions from there, depending on your business logic.
         */
        mockMvc.perform(get(buildUrl("list")))
                .andExpect(status().isOk())
                .andExpect(view().name("book/book-list"))
                .andExpect(model().attribute("bookList", hasSize(2)));
    }

    @Test
    public void testGetNewBookForm() throws Exception {

        verifyZeroInteractions(bookService);

        mockMvc.perform(get(buildUrl("new")))
                .andExpect(status().isOk())
                .andExpect(view().name("book/book-create-or-update"))
                .andExpect(model().attribute("book", instanceOf(Book.class)));
    }

    @Test
    public void testGetDetailedBookView() throws Exception {

        when(bookService.getBookById(anyInt())).thenReturn(new Book());

        mockMvc.perform(get(buildUrl("detail/1")))
                .andExpect(status().isOk())
                .andExpect(view().name("book/book-detail"))
                .andExpect(model().attribute("book", instanceOf(Book.class)));
    }

    @Test
    public void testPostNewBook() throws Exception {
        //Create the properties of the new book separately for testing of values later
        Integer id = 1;
        String title = "HP";
        Integer pageCount = 100;
        Double rating = 4.5;

        //Create the book to be returned by the mock service
        Book newBook = new Book(1, title, pageCount, rating);
        newBook.setId(id);

        //Mock out the service call
        when(bookService.createOrUpdateBook(any(Book.class))).thenReturn(newBook);

        //Mock the POST and check that it is working as expected
        mockMvc
            //Hit the POST URL
            .perform(post(buildUrl("createOrUpdate"))
                //Mock the parameters that would have been sent through automatically on form submission from Spring
                .param("id", id.toString())
                .param("title", title)
                .param("pageCount", pageCount.toString())
                .param("rating", rating.toString()))
            //Assert the status, view and model attributes that are expected
            .andExpect(status().is3xxRedirection()) //We are ensuring that a redirect is happening
            .andExpect(view().name(buildUrl("list", true)))
            .andExpect(model().attribute("book", instanceOf(Book.class)))
            .andExpect(model().attribute("book", hasProperty("id", is(id))))
            .andExpect(model().attribute("book", hasProperty("title", is(title))))
            .andExpect(model().attribute("book", hasProperty("pageCount", is(pageCount))))
            .andExpect(model().attribute("book", hasProperty("rating", is(rating))));
    }

    @Test
    public void testDeleteBook() throws Exception {

        mockMvc
                .perform(get(buildUrl("delete"))
                    .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(buildUrl("list", true)));

        //Check that the deleteBook service was actually invoked in the method
        verify(bookService, times(1)).deleteBook(any(Integer.class));
    }

    private String buildUrl(String localUrl) {
        return buildUrl(localUrl, false);
    }

    private String buildUrl(String localUrl, boolean isRedirect) {
        return (isRedirect ? "redirect:" : "") + "/book/" + localUrl;
    }
}





















