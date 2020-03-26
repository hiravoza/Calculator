package com.rewards.calculator.controller;

import java.util.Date;

import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.calculator.model.Rewards;
import com.rewards.calculator.service.CalculatorService;

@RestController
public class CalculatorController {
	
	@Autowired
	private CalculatorService calculatorService;
	
	@GetMapping(value = "/calculateReward")
    public Rewards getEvents(
    		@RequestParam(required = true) 
    			int userId,
    			
    		@RequestParam(required = true) 
    		@DateTimeFormat(pattern="yyyy-MM")
    		@Pattern(regexp = "^(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$",
            message = "FromDate should be in yyyy-MM-dd format.")
    			Date fromDate,
    			
			@RequestParam(required = true) 
    		@DateTimeFormat(pattern="yyyy-MM")
    		@Pattern(regexp = "^(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$",
            message = "ToDate should be in yyyy-MM-dd format.")
    			Date toDate
            
    ) {
		
		if(toDate.getMonth() - fromDate.getMonth() != 3) {
			return null;
		}
		
		toDate.setMonth(toDate.getMonth() + 1);
		toDate.setDate(toDate.getDate() - 1);
		System.out.println("fromDate:" + fromDate);
		System.out.println("toDate:" + toDate);
		
        return calculatorService.calculate(userId, fromDate, toDate);
    }

}
