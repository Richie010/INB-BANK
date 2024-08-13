package com.mind.service;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mind.dto.AccountDTO;
import com.mind.entity.CurrentAccount;
import com.mind.entity.CurrentTransactions;
import com.mind.entity.SavingsAccount;
import com.mind.entity.TransactionRequest;
import com.mind.exception.AccountNotFoundException;
import com.mind.repository.CurrentAccountRepository;
import com.mind.repository.CurrentTransactionRepository;





@Service
public class CurrentAccountService {
	
	private static final double DAILY_INTEREST_RATE = 0.05;
	private static final double OVERDRAFT_LIMIT_MAX = 200000.0;
	 
	@Autowired
	private CurrentAccountRepository repository;
	@Autowired
	private CurrentTransactionService currentTransactionService; 
	@Autowired
	private CurrentTransactionRepository currentTransactionRepository;
	
	@Autowired
	private SavingsAccountService savingsAccountService;
	
	

	public CurrentAccount createAccount(CurrentAccount account) {
		return repository.save(account);
	}

	public CurrentAccount getAccount(Long id) {
		return repository.findById(id).orElse(null);
	}

	public CurrentAccount updateAccount(CurrentAccount account) {
		return repository.save(account);
	}
	
	public CurrentAccount enableOrDisableOverdraft(Long accountId, boolean enable) {
        
        CurrentAccount account = repository.findById(accountId).orElse(null);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        if (!enable && account.getOverdraftLimit() < OVERDRAFT_LIMIT_MAX) {
            throw new IllegalArgumentException("Overdraft balance Not upto maxmimum limit, Fill it up!!!");
        } else {
            account.setEnableOverdraft(enable);
        }
        return repository.save(account);
    }
	
	public CurrentAccount withdraw(Long accountId,String password, TransactionRequest transactionRequest) {
        CurrentAccount account = repository.findById(accountId).orElse(null);
        if (account == null && password.equals(account.getPassword())) {
            throw new IllegalArgumentException("Account not found");
        }

        double amount = transactionRequest.getAmount();
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        double currentBalance = account.getBalance();
        double currentOverdraft = account.getOverdraftLimit();
        boolean overdraftEnabled = account.isEnableOverdraft();

        if (overdraftEnabled) {
            if (amount <= currentBalance) {
                account.setBalance(currentBalance - amount);
            } else if (amount <= (currentBalance + currentOverdraft)) {
                double overdraftUsed = amount - currentBalance;
                account.setBalance(0);
                account.setOverdraftLimit(currentOverdraft - overdraftUsed);
            } else {
                throw new IllegalArgumentException("Amount exceeds the available balance and overdraft limit");
            }
        } else {
            if (amount <= currentBalance) {
                account.setBalance(currentBalance - amount);
            } else {
                throw new IllegalArgumentException("Insufficient balance");
            }
        }
        CurrentTransactions transhistory=new CurrentTransactions(accountId, amount, "Withdraw");
        currentTransactionRepository.save(transhistory);
        repository.save(account);
       // currentTransactionsService.storeTransactions1(accountId, amount, "Withdraw");
        return account;
    }
	public CurrentAccount withdrawV2(Long accountId, Double amount) {
        CurrentAccount account = repository.findById(accountId).orElse(null);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        double currentBalance = account.getBalance();
        double currentOverdraft = account.getOverdraftLimit();
        boolean overdraftEnabled = account.isEnableOverdraft();

        if (overdraftEnabled) {
            if (amount <= currentBalance) {
                account.setBalance(currentBalance - amount);
            } else if (amount <= (currentBalance + currentOverdraft)) {
                double overdraftUsed = amount - currentBalance;
                account.setBalance(0);
                account.setOverdraftLimit(currentOverdraft - overdraftUsed);
            } else {
                throw new IllegalArgumentException("Amount exceeds the available balance and overdraft limit");
            }
        } else {
            if (amount <= currentBalance) {
                account.setBalance(currentBalance - amount);
            } else {
                throw new IllegalArgumentException("Insufficient balance");
            }
        }

        repository.save(account);
        return account;
    }
	
	public CurrentAccount depositV2(Long accountId, Double amount) {
        CurrentAccount account = repository.findById(accountId).orElse(null);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }

        double currentBalance = account.getBalance();
        double currentOverdraft = account.getOverdraftLimit();
        double initialOverdraft = OVERDRAFT_LIMIT_MAX;

        if (account.isEnableOverdraft()) {
            double overdraftUsed = initialOverdraft - currentOverdraft;
            if (amount <= overdraftUsed) {
                account.setOverdraftLimit(currentOverdraft + amount);
            } else {
                account.setOverdraftLimit(initialOverdraft);
                account.setBalance(currentBalance + (amount - overdraftUsed));
            }
        } else {
            account.setBalance(currentBalance + amount);
        }
        repository.save(account);
        return account;
    }

    public CurrentAccount deposit(Long accountId, String password, TransactionRequest transactionRequest) {
        CurrentAccount account = repository.findById(accountId).orElse(null);
        if (account == null && password.equals(account.getPassword())) {
            throw new IllegalArgumentException("Account not found");
        }

        double amount = transactionRequest.getAmount();
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }

        double currentBalance = account.getBalance();
        double currentOverdraft = account.getOverdraftLimit();
        double initialOverdraft = OVERDRAFT_LIMIT_MAX;

        if (account.isEnableOverdraft()) {
            double overdraftUsed = initialOverdraft - currentOverdraft;
            if (amount <= overdraftUsed) {
                account.setOverdraftLimit(currentOverdraft + amount);
            } else {
                account.setOverdraftLimit(initialOverdraft);
                account.setBalance(currentBalance + (amount - overdraftUsed));
            }
        } else {
            account.setBalance(currentBalance + amount);
        }
        CurrentTransactions transhistory=new CurrentTransactions(accountId, amount, "deposit");
        currentTransactionRepository.save(transhistory);
        repository.save(account);
        return account;
    }
    
    @Transactional
    @Scheduled(cron = "0 * * * * *")
    public void applyDailyInterest() {
        List<CurrentAccount> accounts = repository.findAll();

        for (CurrentAccount account : accounts) {
            if (account.isEnableOverdraft() && account.getOverdraftLimit() < OVERDRAFT_LIMIT_MAX) {
                LocalDate lastInterestDate = account.getLastInterestCalculationDate();
                LocalDate currentDate = LocalDate.now();

                long daysBetween = ChronoUnit.DAYS.between(lastInterestDate, currentDate);

                if (daysBetween > 0) {
                    double overdraftUsed = OVERDRAFT_LIMIT_MAX - account.getOverdraftLimit();
                    double interest = overdraftUsed * DAILY_INTEREST_RATE * daysBetween;
                    account.setOverdraftLimit(account.getOverdraftLimit() - interest);
                    account.setLastInterestCalculationDate(currentDate);

                    repository.save(account);
                    repository.flush();
                }
            }
        }
    }
    
    
    
    public int authenticate(long accountId, String password) {
    	Optional<CurrentAccount>optional=repository.findById(accountId);
    	if(optional.isPresent()&&optional.get().getCount()>=3) {
    		return 2;
    	}
    	else if(optional.isPresent()) {
    		if(optional.get().getPassword().equals(password)) {
    			System.out.println("login success");
    			optional.get().setCount(0);
    			return 0;
    		}
    		else {
    			int currentCount=optional.get().getCount();
    			currentCount+=1;
    			Optional<CurrentAccount> op2=repository.findById(accountId);
    			System.out.println(op2.get().getCount());
    			CurrentAccount savingsAccount=op2.get();
    			savingsAccount.setCount(currentCount);
//    			System.out.println(op2.get().getCount());
    			repository.save(savingsAccount);
    			System.out.println("password wrong");
    			return 1;
    		}
    	}
    	System.out.println("Id is not found");
    	return 1;
    }
    
    
    
    
    
    
    
	 public List<AccountDTO> listOfAccounts(int userId) {
	        List<Object[]> results =  repository.listOfAccount(userId);
	        List<AccountDTO> accountDTOs = new ArrayList<>();
	        
	        for (Object[] result : results) {
	            Long accountId = ((Number) result[0]).longValue();
	            Double balance = ((Number) result[1]).doubleValue();
	            String name = (String) result[2];
	            accountDTOs.add(new AccountDTO(accountId, balance, name));
	        }
	        
	        return accountDTOs;
	    }
	 
	 
	 
	
	 
	 
	 
	 public CurrentAccount getCurrentAccount(Long accountId) {
	        return repository.findById(accountId).orElse(null);
	        		
	    }
	 
	 public CurrentAccount Epay(Long accountId, Double amount, String password) {
		    
		    CurrentAccount account = getCurrentAccount(accountId);

		  
		    if (amount <= 0) {
		        throw new IllegalArgumentException("Transaction amount must be greater than zero.");
		    }

		   
		    if (account == null || !account.getPassword().equals(password)) {
		        throw new IllegalArgumentException("Invalid account or incorrect password.");
		    }

		  
		    double balance = account.getBalance();
		    double overdraftLimit = account.getOverdraftLimit();
		    double totalAvailable = balance + overdraftLimit;

		    // Check if there are sufficient funds (balance + overdraft) for the transaction
		    if (amount > totalAvailable) {
		        throw new IllegalArgumentException("Insufficient funds. Balance: " + balance + ", Overdraft: " + overdraftLimit);
		    }

		  
		    if (amount <= balance) {
		      
		        account.setBalance(balance - amount);
		    } else {
		        
		        double overdraftUsed = amount - balance;
		        account.setBalance(0.0);
		        account.setOverdraftLimit(overdraftLimit - overdraftUsed);
		    }

		    
		    CurrentTransactions transhistory=new CurrentTransactions(accountId, amount, "Epay transaction");
	        currentTransactionRepository.save(transhistory);
		    repository.save(account);
		  

		    return account;
		}

	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 public int transfer(Long sourceAccountId, Long targetAccountId, Double amount, String password) {
		    
		    Optional<CurrentAccount> sourceAccountOptional = repository.findById(sourceAccountId);
		    Optional<CurrentAccount> targetAccountOptional = repository.findById(targetAccountId);
		    
		    if (sourceAccountOptional.isPresent() && targetAccountOptional.isPresent()) {
		        CurrentAccount sourceAccount = sourceAccountOptional.get();
		        CurrentAccount targetAccount = targetAccountOptional.get();
		        
		      
		        if (sourceAccount.getPassword().equals(password)) {
		            double sourceBalance = sourceAccount.getBalance();
		            double overdraftLimit = sourceAccount.getOverdraftLimit();
		          
		            double totalAvailable = sourceBalance + overdraftLimit;
		            
		           
		            if (amount <= totalAvailable) {
		           
		                if (amount <= sourceBalance) {
		                
		                    sourceAccount.setBalance(sourceBalance - amount);
		                } else {
		                    
		                    double overdraftUsed = amount - sourceBalance;
		                    sourceAccount.setBalance(0.0);
		                    sourceAccount.setOverdraftLimit(overdraftLimit - overdraftUsed);
		                }
		                
		               
		                targetAccount.setBalance(targetAccount.getBalance() + amount);
		                //CurrentTransactions(long accountId ,long TargetAccount, double amount, String description) 
		                
		                CurrentTransactions transHistory=new CurrentTransactions(sourceAccountId,targetAccountId , amount, "Amount transfered");
		                currentTransactionRepository.save(transHistory);
		                repository.save(sourceAccount);
		                repository.save(targetAccount);
		                
		                
		                return 2; 
		            } else {
		                return 1;
		            }
		        } else {
		            return 0;
		        }
		    }
		    return 0; 
		}

	 
	 
	 
	 
	 
	 
	 
	 
	 
		public long userDetails(Long accountId){
	    	return repository.userBalance(accountId);
	    }
		
		public Optional<CurrentAccount> getUserDetails(Long accountId){
			return repository.findById(accountId);
			
			
		}
	 
	 
	 
		public long userDetail(Long accountId){
	    	return repository.userBalance(accountId);
	    }
	 
	 
	 
		public Optional<CurrentAccount> listofAccount(Long userId) {
			return repository.findById(userId);
		}
		
	 
		public String userFirstname(Long accountId){
	    	return repository.userName(accountId);
	    }
		
		
		
		
}

