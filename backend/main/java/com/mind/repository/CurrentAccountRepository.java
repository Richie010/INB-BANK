package com.mind.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mind.entity.CurrentAccount;
import com.mind.entity.Registration;
import com.mind.entity.SavingsAccount;



public interface CurrentAccountRepository extends JpaRepository<CurrentAccount, Long> {
	CurrentAccount findByAccountId(int accountId);
	
	//CurrentAccount findByRegistrations(Registration registration);
	//CurrentAccount findByRegistration(Registration registration);
	
	CurrentAccount findByUserId(Long userId);
	
	@Query(value="select name,account_id,email,idproof,phone,city,state from current_Account R where R.account_id LIKE ?1",nativeQuery=true)
	long userDetails(Long accountId);
	
	 
	    
	    @Query(value = "select  balance  ,OVERDRAFT_BALANCE from current_Account R where R.account_id LIKE ?1",nativeQuery = true)
	    long userBalance(Long accountID);
	    
	    
	    @Query(value = "select  name from current_Account R where R.account_id LIKE ?1",nativeQuery = true)
	    String userName(Long accountID);

	    
	    @Query(value = "SELECT account_id, balance, name FROM current_Account WHERE user_id = ?1", nativeQuery = true)
	    List<Object[]> listOfAccount(int userId);
		
	    
}
