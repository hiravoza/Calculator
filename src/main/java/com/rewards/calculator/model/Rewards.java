package com.rewards.calculator.model;

import java.util.Map;

public class Rewards {
	
	Map<Integer, Integer> monthtlyRewards;
	int totalRewardPoints;
	
	public Map<Integer, Integer> getMonthtlyRewards() {
		return monthtlyRewards;
	}
	
	public void setMonthtlyRewards(Map<Integer, Integer> monthtlyRewards) {
		this.monthtlyRewards = monthtlyRewards;
	}
	
	public int getTotalRewardPoints() {
		return totalRewardPoints;
	}
	
	public void setTotalRewardPoints(int totalRewardPoints) {
		this.totalRewardPoints = totalRewardPoints;
	}
	
	
	
	
}
