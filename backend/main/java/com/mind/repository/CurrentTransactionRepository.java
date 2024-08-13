package com.mind.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mind.entity.CurrentTransactions;

public interface CurrentTransactionRepository extends JpaRepository<CurrentTransactions, Integer> {
	@Query(value="SELECT * FROM Currenttransactions where account_id LIKE ?1",nativeQuery = true)
	List<CurrentTransactions> viewTransactionCurrent(long accountId);
}
