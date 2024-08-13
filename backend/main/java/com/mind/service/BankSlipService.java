package com.mind.service;



import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mind.entity.BankSlip;
import com.mind.entity.Cheque;
import com.mind.entity.CurrentAccount;
import com.mind.entity.SavingsAccount;
import com.mind.repository.BankSlipRepo;
import com.mind.repository.ChequeRepo;
import com.mind.repository.CurrentAccountRepository;
import com.mind.repository.SavingsAccountRepository;



@Service
public class BankSlipService {
	
	@Autowired
	private BankSlipRepo bankSlipRepo;
	
	@Autowired
	private ChequeRepo chequeRepo;
	
	@Autowired
	private SavingsAccountRepository savingsAccountRepository;
	
	@Autowired
	private CurrentAccountRepository currentAccountRepository;
	
	@Autowired
	private SavingsAccountService savingsAccountService;
	
	@Autowired
	private CurrentAccountService currentAccountService;
	
	public BankSlip createBankSlip(BankSlip bankSlip) throws AccountNotFoundException {
        Optional<Cheque> chequeOptional = chequeRepo.findById(bankSlip.getChequeId());
        if (!chequeOptional.isPresent()) {
            throw new AccountNotFoundException("Cheque with ID " + bankSlip.getChequeId() + " not found");
        }
        Cheque cheque = chequeOptional.get();
        cheque.setStatus("Sent for Clearance");
        chequeRepo.save(cheque);
        return bankSlipRepo.save(bankSlip);
    }

	public void processCheque(Long chequeId, Long bankSlipId) throws AccountNotFoundException {
	    Optional<Cheque> chequeOptional = chequeRepo.findById(chequeId);
	    Optional<BankSlip> bankSlipOptional = bankSlipRepo.findById(bankSlipId);

	    if (!chequeOptional.isPresent() || !bankSlipOptional.isPresent()) {
	        throw new AccountNotFoundException("Cheque or bank slip not found");
	    }

	    Cheque cheque = chequeOptional.get();
	    BankSlip bankSlip = bankSlipOptional.get();

	    if (!"Sent for Clearance".equals(cheque.getStatus())) {
	        throw new AccountNotFoundException("Cheque is not in 'Sent for Clearance' status");
	    }

	    Double amount = cheque.getAmount();
	    Double bounceFee = 500.0;
	    if (cheque.getSavingsAccountId() != null && bankSlip.getSavingsAccountId() != 0) {
	        Optional<SavingsAccount> senderAccountOptional = savingsAccountRepository.findById(cheque.getSavingsAccountId());
	        Optional<SavingsAccount> receiverAccountOptional = savingsAccountRepository.findById((long) bankSlip.getSavingsAccountId());

	        if (senderAccountOptional.isPresent() && receiverAccountOptional.isPresent()) {
	            SavingsAccount senderAccount = senderAccountOptional.get();
	            SavingsAccount receiverAccount = receiverAccountOptional.get();

	            if (senderAccount.getBalance().compareTo(amount) >= 0) {
	                savingsAccountService.withdrawV2(cheque.getSavingsAccountId(), amount);
	                savingsAccountService.depositV2((long) bankSlip.getSavingsAccountId(), amount);
	                cheque.setStatus("Cleared");
	            } else {
	                cheque.setStatus("Bounced");
	                savingsAccountService.withdraw(cheque.getSavingsAccountId(), bounceFee);
	            }

	            chequeRepo.save(cheque);
	            savingsAccountRepository.save(senderAccount);
	            savingsAccountRepository.save(receiverAccount);

	        } else {
	            throw new AccountNotFoundException("Sender or receiver savings account not found");
	        }
	    } else if (cheque.getCurrentAccountId() != 0 && bankSlip.getCurrentAccountId() != 0) {
	        Optional<CurrentAccount> senderAccountOptional = currentAccountRepository.findById(cheque.getCurrentAccountId());
	        Optional<CurrentAccount> receiverAccountOptional = currentAccountRepository.findById((long)bankSlip.getCurrentAccountId());

	        if (senderAccountOptional.isPresent() && receiverAccountOptional.isPresent()) {
	            CurrentAccount senderAccount = senderAccountOptional.get();
	            CurrentAccount receiverAccount = receiverAccountOptional.get();
	            System.out.println(senderAccount);
	            System.out.println(receiverAccount);
	            if (senderAccount.getOverdraftLimit() >= amount.doubleValue()) {
	                currentAccountService.withdrawV2(cheque.getCurrentAccountId(), amount);
	                currentAccountService.depositV2(bankSlip.getCurrentAccountId(), amount);
	                cheque.setStatus("Cleared");
	            } else {
	                cheque.setStatus("Bounced");
	                currentAccountService.withdrawV2(cheque.getCurrentAccountId(), bounceFee);
	            }

	            chequeRepo.save(cheque);
	            currentAccountRepository.save(senderAccount);
	            currentAccountRepository.save(receiverAccount);

	        } else {
	            throw new AccountNotFoundException("Sender or receiver current account not found");
	        }
	    } else if (cheque.getSavingsAccountId() != null && bankSlip.getCurrentAccountId() != null) {
	        Optional<SavingsAccount> senderAccountOptional = savingsAccountRepository.findById(cheque.getSavingsAccountId());
	        Optional<CurrentAccount> receiverAccountOptional = currentAccountRepository.findById(bankSlip.getCurrentAccountId());

	        if (senderAccountOptional.isPresent() && receiverAccountOptional.isPresent()) {
	            SavingsAccount senderAccount = senderAccountOptional.get();
	            CurrentAccount receiverAccount = receiverAccountOptional.get();

	            if (senderAccount.getBalance().compareTo(amount) >= 0) {
	                savingsAccountService.withdraw(cheque.getSavingsAccountId(), amount);
	                currentAccountService.depositV2((long)bankSlip.getCurrentAccountId(), amount);
	                cheque.setStatus("Cleared");
	            } else {
	                cheque.setStatus("Bounced");
	                savingsAccountService.withdraw(cheque.getSavingsAccountId(), bounceFee);
	            }

	            chequeRepo.save(cheque);
	            savingsAccountRepository.save(senderAccount);
	            currentAccountRepository.save(receiverAccount);

	        } else {
	            throw new AccountNotFoundException("Sender savings account or receiver current account not found");
	        }
	    } else if (cheque.getCurrentAccountId() != 0 && bankSlip.getSavingsAccountId() != 0) {
	        Optional<CurrentAccount> senderAccountOptional = currentAccountRepository.findById(cheque.getCurrentAccountId());
	        Optional<SavingsAccount> receiverAccountOptional = savingsAccountRepository.findById((long) bankSlip.getSavingsAccountId());

	        if (senderAccountOptional.isPresent() && receiverAccountOptional.isPresent()) {
	            CurrentAccount senderAccount = senderAccountOptional.get();
	            SavingsAccount receiverAccount = receiverAccountOptional.get();

	            if (senderAccount.getOverdraftLimit() >= amount.doubleValue()) {
	                currentAccountService.withdrawV2(cheque.getCurrentAccountId(), amount);
	                savingsAccountService.depositV2((long) bankSlip.getSavingsAccountId(), amount);
	                cheque.setStatus("Cleared");
	            } else {
	                cheque.setStatus("Bounced");
	                currentAccountService.withdrawV2(cheque.getCurrentAccountId(), bounceFee);
	            }

	            chequeRepo.save(cheque);
	            currentAccountRepository.save(senderAccount);
	            savingsAccountRepository.save(receiverAccount);

	        } else {
	            throw new AccountNotFoundException("Sender current account or receiver savings account not found");
	        }

	    } else {
	        throw new AccountNotFoundException("Cheque does not reference a valid account");
	    }
	}
}
