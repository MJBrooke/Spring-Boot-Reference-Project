package com.mike.form;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Component
@SessionScope
public class BmiForm {

    @Min(value = 1, message = "Please enter a number greater than, or equal to 1")
    @Max(value = 250, message = "Please enter a number less than, or equal to 250")
    private BigDecimal height;
    private BigDecimal weight;
    private Integer waistCircumference;

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Integer getWaistCircumference() {
        return waistCircumference;
    }

    public void setWaistCircumference(Integer waistCircumference) {
        this.waistCircumference = waistCircumference;
    }
}