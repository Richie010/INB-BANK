package com.mind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mind.entity.CurrentTransactions;
import com.mind.repository.CurrentTransactionRepository;

@Service
public class CurrentTransactionService {
	@Autowired
	private CurrentTransactionRepository currentTransactionRepository;
	
	public List<CurrentTransactions> viewTransactionCurrent(long accountId){
		return currentTransactionRepository.viewTransactionCurrent(accountId);
	}
	
	public CurrentTransactions storeTransactionCurrent(CurrentTransactions currentTransactions) {
		return currentTransactionRepository.save(currentTransactions);
	}

}
