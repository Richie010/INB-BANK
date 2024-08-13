package com.mind.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mind.dto.AccountRequest;
import com.mind.dto.MultiAccountRequest;
import com.mind.entity.AdminEntity;
import com.mind.entity.Registration;
import com.mind.entity.SavingsAccount;
import com.mind.exception.AccountNotFoundException;
import com.mind.repository.RegistrationRepository;
import com.mind.repository.SavingsAccountRepository;

import java.util.*;
import java.util.Optional;



@Service
public class RegistrationService {
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private SavingsAccountRepository savingsAccountRepository;

    public Registration registerUser(Registration registration) {
        return registrationRepository.save(registration);
    }
    
//    public Registration existRegister(long userId,String accountType,String email,double balance,String password) throws AccountNotFoundException{
//		Registration existingRegister=registrationRepository.findByUserId(userId);
//		if(existingRegister!=null) {
//			Registration newRegisteration=new Registration();
//			newRegisteration=registrationRepository.findByUserId(userId);
//			newRegisteration.setUserId(userId);
//			newRegisteration.setAccountType(accountType);
//			newRegisteration.setBalance(balance);
//			newRegisteration.setEmail(email);
//			newRegisteration.setPassword(password);
//			newRegisteration.setApproved(false);
//			registrationRepository.save(newRegisteration);
//			return newRegisteration;
//		}
//		else {
//			throw new AccountNotFoundException("Id is not found!!");
//		}
//    }
    
    public Registration existRegisterAccount(long userId,double balance,String password,String email,String accountType) throws AccountNotFoundException{
		Optional<Registration> optional=registrationRepository.findById(userId);
		Registration existsavingsAccount=optional.get();
		if(optional.isPresent()) {
			Registration newSavingsAccount=new Registration(userId,balance,existsavingsAccount.getName(),email,password,accountType,existsavingsAccount.getPhone(),existsavingsAccount.getCity(),existsavingsAccount.getState(),existsavingsAccount.getDob(),existsavingsAccount.getIdproof(),existsavingsAccount.getisApproved());
			registrationRepository.save(newSavingsAccount);
			return newSavingsAccount;
		}
		else {
			throw new AccountNotFoundException("Id is not found!!");
		}
    }
    
    
    
   
    
    
    

    
    public boolean authenticate(String email, String password) {
    	Registration admin = registrationRepository.findByemail(email);
    	 System.out.println("login success");
        return admin != null && admin.getPassword().equals(password);
       
    }

    public List<Registration> getPendingRegistrations() {
        return registrationRepository.findAll().stream()
                .filter(reg -> !reg.getisApproved())
                .toList();
    }
    public List<Registration> getPendingApprovals() {
        return registrationRepository.findByisApprovedFalse();
    }
  

    public Registration approveUser(Long userId) {
        Registration registration = registrationRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        registration.setApproved(true);
        return registrationRepository.save(registration);
    }
    
    
}