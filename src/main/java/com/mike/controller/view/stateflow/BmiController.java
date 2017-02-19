package com.mike.controller.view.stateflow;

import com.mike.form.BmiForm;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/stateflow")
public class BmiController {

    private BmiForm sessionBmiForm;

    public BmiController(BmiForm sessionBmiForm) {
        this.sessionBmiForm = sessionBmiForm;
    }

    @GetMapping("/bmi")
    public String inputBmi(Model model) {

        model.addAttribute("bmiForm", sessionBmiForm);
        return "stateflow/bmi-input";
    }

    @PostMapping("/bmi")
    public String postBmi(@Valid @ModelAttribute BmiForm bmiForm, BindingResult bindingResult) {

        String view;

        if (bindingResult.hasErrors()) {
            view = "stateflow/bmi-input";
        } else {
            BeanUtils.copyProperties(bmiForm, sessionBmiForm);
            view = "redirect:/stateflow/bloodPressure";
        }

        return view;
    }
}