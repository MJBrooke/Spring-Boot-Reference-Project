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

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        mockMvc.perform(get("/book/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/book-list"))
                .andExpect(model().attribute("bookList", hasSize(2)));
    }
}





















