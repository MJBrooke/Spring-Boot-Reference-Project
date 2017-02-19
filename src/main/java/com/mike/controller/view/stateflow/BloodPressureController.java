package com.mike.controller.view.stateflow;

import com.mike.form.BloodPressureForm;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stateflow")
public class BloodPressureController {

    private BloodPressureForm bloodPressureForm;

    public BloodPressureController(BloodPressureForm bloodPressureForm) {
        this.bloodPressureForm = bloodPressureForm;
    }

    @GetMapping("/bloodPressure")
    public String inputBloodPressure(Model model) {

        model.addAttribute("bloodPressure", bloodPressureForm);
        return "stateflow/blood-pressure-input";
    }

    @PostMapping("/bloodPressure")
    public String postBloodPressure(@ModelAttribute BloodPressureForm bloodPressure) {

        //TODO - add validation;

        BeanUtils.copyProperties(bloodPressure, bloodPressureForm);
        return "redirect:/stateflow/resultsPage";
    }
}