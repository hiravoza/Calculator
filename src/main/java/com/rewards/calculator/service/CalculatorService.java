package com.rewards.calculator.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewards.calculator.model.ResponseEntity;
import com.rewards.calculator.model.Rewards;
import com.rewards.calculator.model.Transactions;
import com.rewards.calculator.repository.TransactionRepository;

@Service
public class CalculatorService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	public ResponseEntity calculate(int userId, Date fromDate, Date toDate) {
		
		List<Transactions> transactions =  transactionRepository.getTransactions(userId, fromDate, toDate);
		
		Rewards rewards = new Rewards();
		
		Map<Integer, Integer> monthlyRewards = new HashMap<Integer, Integer>();
		int totalRewards = 0;
		
		for(Transactions trans: transactions) {
			int sub = 0;
			if(trans.getAmount() > 50 && trans.getAmount() <= 100) {
				sub = (trans.getAmount() - 50);
			} else if(trans.getAmount() > 100) {
				sub = ((trans.getAmount() - 100) * 2) + 50;
			} else {
				continue;
			}
			
			if(monthlyRewards.get(trans.getDate().getMonth()+1) == null) {
				monthlyRewards.put(trans.getDate().getMonth() + 1, sub);
			} else {
				monthlyRewards.put(trans.getDate().getMonth(), monthlyRewards.get(trans.getDate().getMonth() + 1) + sub);
			}
			totalRewards +=  sub;
		}
		
		rewards.setMonthtlyRewards(monthlyRewards);
		rewards.setTotalRewardPoints(totalRewards);
		
		return new ResponseEntity(200, "SUCCESS", rewards);
	}
	
	public static final int getMonthsDifference(Date date1, Date date2) {
	    int m1 = date1.getYear() * 12 + date1.getMonth();
	    int m2 = date2.getYear() * 12 + date2.getMonth();
	    return m2 - m1 + 1;
	}

}
