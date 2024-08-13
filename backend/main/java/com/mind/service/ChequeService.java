package com.mind.service;



import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mind.entity.Cheque;
import com.mind.entity.CurrentAccount;
import com.mind.entity.SavingsAccount;
import com.mind.repository.ChequeRepo;
import com.mind.repository.CurrentAccountRepository;
import com.mind.repository.SavingsAccountRepository;



@Service
public class ChequeService {
	
	@Autowired
	private ChequeRepo chequeRepo;
	
	@Autowired
	private SavingsAccountRepository savingsAccountRepository;
	
	@Autowired
	private CurrentAccountRepository currentAccountRepository;
	
	public Cheque storeChequeDetails(Cheque cheque) throws AccountNotFoundException {
        if (cheque.getSavingsAccountId() != null) {
            Optional<SavingsAccount> savingsAccount = savingsAccountRepository.findById(cheque.getSavingsAccountId());
            if (!savingsAccount.isPresent()) {
                throw new AccountNotFoundException("Savings account with ID " + cheque.getSavingsAccountId() + " not found");
            }
        } else if (cheque.getCurrentAccountId() != null) {
            Optional<CurrentAccount> currentAccount = currentAccountRepository.findById(cheque.getCurrentAccountId());
            if (!currentAccount.isPresent()) {
                throw new AccountNotFoundException("Current account with ID " + cheque.getCurrentAccountId() + " not found");
            }
        } else {
            throw new AccountNotFoundException("Neither savings account ID nor current account ID is provided");
        }
        return chequeRepo.save(cheque);
    }
	

}
