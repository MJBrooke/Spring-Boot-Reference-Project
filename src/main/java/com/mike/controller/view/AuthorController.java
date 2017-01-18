package com.mike.controller.view;

import com.mike.domain.Author;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class AuthorController {

    @GetMapping("/author/list")
    public String getAuthorList(Model model) {

        //TODO :: migrate this to the CrudRepository for Author once it is done

        ArrayList<Author> authorList = new ArrayList<>();
        authorList.add(new Author(1, "Robert Galbraith", "Joanne", "Rowling", 38));

        model.addAttribute("authorList", authorList);

        return "author/author-list";
    }
}
