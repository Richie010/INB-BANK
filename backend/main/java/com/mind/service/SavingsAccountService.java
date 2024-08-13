

package com.mind.service;

import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mind.dto.AccountDTO;
import com.mind.entity.AdminEntity;
import com.mind.entity.CurrentAccount;
import com.mind.entity.Registration;
import com.mind.entity.SavingsAccount;
import com.mind.entity.Transaction;
import com.mind.exception.AccountNotFoundException;
import com.mind.repository.CurrentAccountRepository;
import com.mind.repository.RegistrationRepository;
import com.mind.repository.SavingsAccountRepository;


import javax.transaction.Transactional;

@Service
public class SavingsAccountService {
	@Autowired
	private RegistrationRepository registrationRepository;
    @Autowired
    private SavingsAccountRepository savingsAccountRepository;
    @Autowired
    private SavingsTransactionsService savingsTransactionsService;
    @Autowired
    private CurrentAccountRepository currentAccountRepository;

    public SavingsAccount getSavingsAccount(Long accountId) {
        return savingsAccountRepository.findById(accountId).orElse(null);
        		
    }
    
    
    
    public SavingsAccount createAccount(SavingsAccount account) {
        return savingsAccountRepository.save(account);
    }
    
//   String name, String email, String isalreadyexist, int phone, String city, String state,
//	Date dob, int userId, int idproof, Double balance, int count, Double minimumBalance,
//	Double dailyWithdrawalLimit, String password
// 
	public SavingsAccount existSavingAccount(long userId, double balance, String password, String email)
			throws AccountNotFoundException {
		Optional<Registration> optional=registrationRepository.findById(userId);
		if(optional.isPresent()) {
			Registration existingsavings=optional.get();
			SavingsAccount newsavings=new SavingsAccount(existingsavings.getName(),email,"1",existingsavings.getPhone(),existingsavings.getCity(),existingsavings.getState(),existingsavings.getDob(),(int) userId,existingsavings.getIdproof(),balance,0,2500.0,10000.0,password);
			savingsAccountRepository.save(newsavings);
			return newsavings;
		}
		else {
			throw new AccountNotFoundException("Id is not found");
		}
	}
	
//	String name, int phone, String city, String state, Date dob, long userId,
//	int idproof, double balance, String email, int count, LocalDate lastInterestCalculationDate2,
//	double overdraftLimit, String password
	
	public CurrentAccount existCurrentAccount(long userId, double balance, String password, String email)
			throws AccountNotFoundException {
		Optional<Registration> optional=registrationRepository.findById(userId);
		if(optional.isPresent()) {
			Registration existingsavings=optional.get();
			CurrentAccount newcurrent=new CurrentAccount(existingsavings.getName(),existingsavings.getPhone(),existingsavings.getCity(),existingsavings.getState(),existingsavings.getDob(),(int) userId,existingsavings.getIdproof(),balance,email,0,250000,password);
			currentAccountRepository.save(newcurrent);
			return newcurrent;
		}
		else {
			throw new AccountNotFoundException("Id is not found");
		}
	}
//    public SavingsAccount existSavingAccount2(int userId,double balance,String password,String email) throws AccountNotFoundException{
//  		SavingsAccount existsavingsAccount=savingsAccountRepository.findByUserId(userId);
//  		if(existsavingsAccount!=null) {
//  			//String name, Integer phone, String city, String state, Date dob, int userId, Integer idproof,
////			Double balance,String email,String isalreadyexist,  int count, Double minimumBalance, Date lastWithdrawalDate, Double dailyWithdrawalLimit,
////			String password
//  			SavingsAccount existing=new SavingsAccount(existsavingsAccount.getName(),existsavingsAccount.getPhone(),existsavingsAccount.getCity(),existsavingsAccount.getState(),existsavingsAccount.getDob(),userId,existsavingsAccount.getIdproof(),balance,email,"1",0,existsavingsAccount.getMinimumBalance(),existsavingsAccount.getLastWithdrawalDate(),existsavingsAccount.getDailyWithdrawalLimit(),password);
//  			savingsAccountRepository.save(existing);
//  			return existing;
//  		}
//  		else {
//  			throw new AccountNotFoundException("Id is not found!!");
//  		}
//      }
    
    
    public int authenticate(Long accountId, String password) {
    	Optional<SavingsAccount>optional=savingsAccountRepository.findById(accountId);
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
    			Optional<SavingsAccount> op2=savingsAccountRepository.findById(accountId);
//    			System.out.println(op2.get().getCount());
    			
    			SavingsAccount savingsAccount=op2.get();
    			savingsAccount.setCount(currentCount);
//    			System.out.println(op2.get().getCount());
    			savingsAccountRepository.save(savingsAccount);
    			System.out.println("password wrong");
    			return 1;
    		}
    	}
    	System.out.println("Id is not found");
    	return 1;
    }
//    	 System.out.println("login success");
//        return admin != null && admin.getPassword().equals(password);
       
    
    

    @Transactional
    public boolean withdraw(Long accountId,  Double amount) {
    	double minimumBalance=2500;
        SavingsAccount account = getSavingsAccount(accountId);
        if(amount<=0) {
        	throw new IllegalArgumentException("Withdrawl amount over");
        }
        if (account != null && account.withdraw(amount)) {
            savingsAccountRepository.save(account);
            savingsTransactionsService.storeTransactions(accountId, null, amount,"Withdraw");
            return true;
        }
        return false;
    }
    @Transactional
    public boolean withdraw2(Long accountId, String password, Double amount) {
    	double minimumBalance=2500;
        SavingsAccount account = getSavingsAccount(accountId);
        if(amount<=0) {
        	throw new IllegalArgumentException("Withdrawl amount over");
        }
        if (account != null && account.withdraw(amount) && password.equals(account.getPassword())) {
            savingsAccountRepository.save(account);
            savingsTransactionsService.storeTransactions(accountId, null, amount,"Withdraw");
            return true;
        }
        return false;
    }
    @Transactional
    public boolean withdrawV2(long accountId, Double amount) {
    	double minimumBalance=2500;
        SavingsAccount account = getSavingsAccount( accountId);
        if(amount<=0) {
        	throw new IllegalArgumentException("Withdrawl amount over");
        }
        if (account != null && account.withdraw(amount)) {
            savingsAccountRepository.save(account);
            savingsTransactionsService.storeTransactions1(accountId, amount,"Withdraw");
            return true;
        }
        return false;
    }

    @Transactional
    public void deposit(Long accountId,  String password,Double amount) {
    	
        SavingsAccount account = getSavingsAccount(accountId);
        if (account != null && password.equals(account.getPassword())) {
            account.deposit(amount);
            savingsAccountRepository.save(account);
            savingsTransactionsService.storeTransaction(accountId, amount,"deposit");
        }
    }
    @Transactional
    public void depositV2(Long accountId, double amount) {
    	
        SavingsAccount account = getSavingsAccount(accountId);
        if (account != null) {
            account.deposit(amount);
            savingsAccountRepository.save(account);
        }
    }

    public int transfer(Long sourceAccountId, Long targetAccountId, Double amount,String password) {
    	Optional<SavingsAccount> optional=savingsAccountRepository.findById(sourceAccountId);
    	Optional<SavingsAccount> optional2=savingsAccountRepository.findById(targetAccountId);
    	if(optional.isPresent()&&optional2.isPresent()&& optional.get().getPassword().equals(password)) {
    		if(optional.get().getBalance()>amount) {

    			SavingsAccount savingsAccount=optional.get();
    			SavingsAccount savingsAccount1=optional2.get();
    			savingsAccount.setBalance(optional.get().getBalance()-amount);
    			savingsAccount1.setBalance(optional2.get().getBalance()+amount);
    			savingsAccountRepository.save(savingsAccount);
    			savingsAccountRepository.save(savingsAccount1);
    			savingsTransactionsService.storeTransactions(sourceAccountId, targetAccountId,amount,"Transfer money");
    			return 2;
    		}
    		else {
    			return 1;
    		}
    	}
		return 0;
        
    }
    
    
    public SavingsAccount withdraw1(Long accountId, String password, Double amount) {
        
        SavingsAccount account = getAccountId(accountId);

      
        if (!account.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password.");
        }

      
        Date now = new Date();

      
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date startOfCurrentDay = cal.getTime();

       
        if (account.getLastWithdrawalDate() != null) {
            cal.setTime(account.getLastWithdrawalDate());
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            Date startOfLastWithdrawalDay = cal.getTime();

            if (!startOfCurrentDay.equals(startOfLastWithdrawalDay)) {
                account.setLastWithdrawalDate(startOfCurrentDay);
            }
        } else {
           
            account.setLastWithdrawalDate(startOfCurrentDay);
        }

       
        double newBalance = account.getBalance() - amount;
        if (newBalance < account.getMinimumBalance()) {
            throw new IllegalArgumentException("Insufficient funds. Minimum balance required is " + account.getMinimumBalance());
        }

       
        double dailyWithdrawnAmount = account.getDailyWithdrawalLimit() - amount;
        if (dailyWithdrawnAmount < 0) {
            throw new IllegalArgumentException("Daily withdrawal limit reached.");
        }

        
        boolean withdrawalSuccessful = account.withdraw(amount);
        if (withdrawalSuccessful) {
        
            account.setBalance(newBalance);
            account.setLastWithdrawalDate(now);

          
            SavingsAccount updatedAccount = savingsAccountRepository.save(account);

            
            savingsTransactionsService.storeTransactions1(accountId, amount, "Withdraw");

            return updatedAccount;
        } else {
            throw new IllegalStateException("Withdrawal operation failed.");
        }
    }



	private SavingsAccount getAccountId(Long accountId) {
		// TODO Auto-generated method stub
		return savingsAccountRepository.findByAccountId(accountId);
		
	}
	
	
	
	public List<Registration> getAllDetails(){
		return registrationRepository.findAll().stream().filter(reg-> !reg.getAccountType()
				.contains("SAVINGS")).toList();
		
	}
	
	public Optional<SavingsAccount> getUserDetails(Long accountId){
		return savingsAccountRepository.findById(accountId);
		
		
	}
	
//	public long Listofaccounts(int userId) {
//		return savingsAccountRepository.listofAccount(userId);
//	}
//	
	 public List<AccountDTO> listOfAccounts(int userId) {
	        List<Object[]> results = savingsAccountRepository.listOfAccount(userId);
	        List<AccountDTO> accountDTOs = new ArrayList<>();
	        
	        for (Object[] result : results) {
	            Long accountId = ((Number) result[0]).longValue();
	            Double balance = ((Number) result[1]).doubleValue();
	            String name = (String) result[2];
	            accountDTOs.add(new AccountDTO(accountId, balance, name));
	        }
	        
	        return accountDTOs;
	    }
	
	public long userDetails(Long accountId){
    	return savingsAccountRepository.userDetails(accountId);
    }
	
	public Optional<SavingsAccount> listofAccount(Long userId) {
		return savingsAccountRepository.findById(userId);
	}
	public String userFirstname(Long accountId){
    	return savingsAccountRepository.username(accountId);
    }


 


	public SavingsAccount Epay(Long accountId, Double amount,String password) {
		
		  SavingsAccount account = getSavingsAccount(accountId);
			
	        if(amount<=0 && account.getPassword().equals(password)) {
	        	throw new IllegalArgumentException("Transaction  amount failed");
	        }
	        if (account != null && account.epay(amount)) {
	            savingsAccountRepository.save(account);
	            savingsTransactionsService.storeTransactions1(accountId, amount, "Epay");
	            return account ;
	        }
	        return account ;
	    
	}
//	
//	 public List<SavingsAccount> getAccountsForUsersWithMultipleAccounts() {
//	        // Step 1: Get the list of user IDs with multiple savings accounts
//	        List<Integer> userIds = savingsAccountRepository.findUserIdsWithMultipleAccounts();
//	        
//	        // Step 2: Fetch the savings accounts for these user IDs
//	        if (userIds.isEmpty()) {
//	            return Collections.emptyList(); // No users with multiple accounts
//	        }
//	        return savingsAccountRepository.findSavingsAccountsByUserIds(userIds);
//	    }
	
	
	
	
	}
	



