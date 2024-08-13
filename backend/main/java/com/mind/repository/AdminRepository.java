package com.mind.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mind.dto.ChequeBankSlipDTO;
import com.mind.entity.AdminEntity;
import com.mind.entity.BankSlip;
import com.mind.entity.Cheque;
import com.mind.entity.Registration;
import com.mind.entity.SavingsAccount;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
	AdminEntity findByUsername(String username);
	@Query(value="SELECT * FROM savings_Account where IS_ALREADYEXISTS like '1'",nativeQuery=true )
	List<SavingsAccount> getexistpending();
	
	@Query(value = "select * from cheque where status='Sent for Clearance' ",nativeQuery = true)
	List<Object[]> getpending();
	
	@Query(value = "select cheque_id,amount from bank_Slips  ",nativeQuery = true)
	List<Object[]> getbankslips();
	
	
	 @Query(value = "SELECT c.cheque_id AS chequeId,b.bank_slip_id AS bankSlipId  FROM cheque c JOIN bank_slips b ON c.cheque_id = b.cheque_id WHERE status = 'Sent for Clearance'", nativeQuery = true)
	 List<Object[]> findChequesAndBankSlipsSentForClearance();
	//WHERE ALREADY_EXIST LIKE '1'
}