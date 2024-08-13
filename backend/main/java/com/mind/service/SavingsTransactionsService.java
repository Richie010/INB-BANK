package com.mind.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mind.entity.SavingsTransactions;
import com.mind.repository.SavingsTransactionsRepository;


@Service
public class SavingsTransactionsService {
	@Autowired
	private SavingsTransactionsRepository transactionsRepository;
	
	public SavingsTransactions storeTransactions(Long accountId,Long TargetAccount,double amount,String description) {
		SavingsTransactions svt=new SavingsTransactions(accountId,TargetAccount ,amount, description);
		return transactionsRepository.save(svt);
	}
	public SavingsTransactions storeTransaction(Long accountId,double amount,String description) {
		SavingsTransactions svt=new SavingsTransactions(accountId ,amount, description);
		return transactionsRepository.save(svt);
	}
	public SavingsTransactions storeTransactions1(Long accountId,double amount,String description) {
		SavingsTransactions savingstrans=new SavingsTransactions(accountId ,amount, description);
		return transactionsRepository.save(savingstrans);
	}
	
	public List<SavingsTransactions> viewLastFiveTransaction(Long accountId){
		return transactionsRepository.viewLastFiveTransaction(accountId);
	}

}
