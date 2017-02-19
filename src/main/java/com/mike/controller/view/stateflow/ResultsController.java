package com.mike.controller.view.stateflow;

import com.mike.form.BmiForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stateflow")
public class ResultsController {

    private BmiForm bmiForm;

    public ResultsController(BmiForm bmiForm) {
        this.bmiForm = bmiForm;
    }

    @GetMapping("/resultsPage")
    public String seeStateflowResults(Model model) {

        model.addAttribute("bmi", bmiForm);
        return "stateflow/results";
    }
}