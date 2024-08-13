package com.mind.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mind.dto.AccountDTO;
import com.mind.entity.Registration;
import com.mind.entity.SavingsAccount;



public interface SavingsAccountRepository extends JpaRepository<SavingsAccount,Long> {

	SavingsAccount findByAccountId(Long accountId);
	//SavingsAccount findById(int accountId );

	SavingsAccount findByRegistration(Registration registration);
	SavingsAccount findByUserId(int userId);
	

	
	//List<SavingsAccount> findByisAlreadyTrue();
	
	@Query(value="select account_id ,balance , name from savings_Account R where R.user_id Like ?1" , nativeQuery = true)
	List<Long>listofAccount (int userId);
	
	
    @Query(value = "SELECT account_id, balance, name FROM savings_Account WHERE user_id = ?1", nativeQuery = true)
    List<Object[]> listOfAccount(int userId);
	
	
	@Query(value="select balance from SAVINGS_ACCOUNT R where R.account_id LIKE ?1",nativeQuery=true)
	long userDetails(Long accountId);
	
@Query(value ="select name from SAVINGS_ACCOUNT R where R.account_Id LIKE ?1" , nativeQuery = true)
	String username(Long accountId);
//Optional<SavingsAccount> findByid(Long savingsAccountId);
//Optional<SavingsAccount> findById(int savingsAccountId);
}

//@Query(value="SELECT * FROM savings_account WHERE is_alreadyExists = 0	AND user_id IN ( SELECT user_id	FROM savings_account WHERE is_alreadyExists = 0	 GROUP BY user_id HAVING COUNT(*) > 1);	",nativeQuery = true)
//int userExist(int user_id);
//
//	
//@Query(value = "SELECT user_id FROM savings_account WHERE is_alreadyExists = 0 GROUP BY user_id HAVING COUNT(*) > 1", nativeQuery = true)
//List<Integer> findUserIdsWithMultipleAccounts();
//
//
//
//@Query("SELECT sa FROM SavingsAccount sa WHERE sa.userId IN :userIds")
//List<SavingsAccount> findSavingsAccountsByUserIds(@Param("userIds") List<Integer> userIds);
//	 
//}
