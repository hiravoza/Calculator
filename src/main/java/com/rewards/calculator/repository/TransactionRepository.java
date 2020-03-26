package com.rewards.calculator.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rewards.calculator.model.Transactions;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Integer> {
	
	@Query("SELECT t from Transactions t where userId = :userId and date between :fromDate and :toDate order by date")
	List<Transactions> getTransactions(int userId, Date fromDate, Date toDate);
}
