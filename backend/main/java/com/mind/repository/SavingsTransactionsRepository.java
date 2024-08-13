package com.mind.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mind.entity.SavingsTransactions;

public interface SavingsTransactionsRepository extends JpaRepository<SavingsTransactions, Integer>{
	
	@Query(value = "SELECT * FROM transactions  where account_id LIKE ?1",nativeQuery = true)
	List<SavingsTransactions> viewLastFiveTransaction(Long accountId);
}
