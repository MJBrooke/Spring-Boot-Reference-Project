package com.mike.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
This is a simple example of the minimum code needed for a controller.
The class is annotated as such, so that it can be instantiated as a bean.
It then maps a request pattern to /, and returns the name of the view to be rendered.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String initialIndexPage() {
        return "index";
    }
}
