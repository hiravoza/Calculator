package com.rewards.calculator.controller;

import java.time.Period;
import java.util.Date;

import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.calculator.model.ResponseEntity;
import com.rewards.calculator.model.Rewards;
import com.rewards.calculator.service.CalculatorService;

@RestController
public class CalculatorController {
	
	@Autowired
	private CalculatorService calculatorService;
	
	@GetMapping(value = "/calculateReward")
    public ResponseEntity getEvents(
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
		
		int diff = calculatorService.getMonthsDifference(fromDate, toDate);
		if(diff > 3) {
			return new ResponseEntity(422, "Gap can't be more than 3 months", null);
		} else if(diff < 0) {
			return new ResponseEntity(422, "To date needs to be greater than from date", null);
		}
		
		
		toDate.setMonth(toDate.getMonth() + 1);
		toDate.setDate(toDate.getDate() - 1);
		System.out.println("fromDate:" + fromDate);
		System.out.println("toDate:" + toDate);
		
        return calculatorService.calculate(userId, fromDate, toDate);
    }

}
