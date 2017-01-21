package com.mike.controller.view;

import com.mike.domain.Book;
import com.mike.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
A more complicated controller than the IndexController.
Uses a global controller mapping, which is prepended to each individual request mapping contained within.
Also shows how to autowire a service in, and store its returned objects into the model for the web page.
 */
@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping({"", "/list"})
    public String getBookList(Model model) {

        model.addAttribute("bookList", bookService.getBookList());

        return "book/book-list";
    }

    @GetMapping("/new")
    public String getNewProductForm(Model model) {

        model.addAttribute("book", new Book());

        return "book/book-create-or-update";
    }

    @GetMapping("/detail/{id}")
    public String getDetailedBookView(@PathVariable Integer id, Model model) {

        model.addAttribute("book", bookService.getBookById(id));

        return "book/book-detail";
    }

    /*
    Example of a request that uses a path variable to retrieve the ID
     */
    @GetMapping("/update/{id}")
    public String getUpdateBookForm(@PathVariable Integer id, Model model) {

        model.addAttribute("book", bookService.getBookById(id));

        return "book/book-create-or-update";

    }

    /*
    Example of a POST that has had the form object bind to a parameter using @ModelAttribute
     */
    @PostMapping("/createOrUpdate")
    public String postNewOrUpdatedBook(@ModelAttribute Book book) {

        bookService.createOrUpdateBook(book);

        /*
        A redirect works differently to when the name of a view is given.
        A redirect will send the browser to the URL that follows the ':', on which a GET will be performed.
         */
        return "redirect:/book/list";
    }

    /*
    An example of a POST that uses request parameters to retrieve the relevant ID
    PS. I realise this is not as good as using PathVariables - it is just an example of how it could be done
     */
    @GetMapping("/delete")
    public String deleteBook(@RequestParam("id") int id) {

        bookService.deleteBook(id);

        return "redirect:/book/list";
    }

}
