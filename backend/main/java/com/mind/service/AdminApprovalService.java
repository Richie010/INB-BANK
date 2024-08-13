package com.mind.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mind.dto.ChequeBankSlipDTO;
import com.mind.entity.AdminEntity;
import com.mind.entity.BankSlip;
import com.mind.entity.Cheque;
import com.mind.entity.CurrentAccount;
import com.mind.entity.Registration;
import com.mind.entity.SavingsAccount;
import com.mind.repository.AdminRepository;
import com.mind.repository.BankSlipRepo;
import com.mind.repository.CurrentAccountRepository;
import com.mind.repository.RegistrationRepository;
import com.mind.repository.SavingsAccountRepository;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class AdminApprovalService {

    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private BankSlipRepo bankrepo;
    
    @Autowired
    private SavingsAccountRepository savingsAccountRepo;
    
    @Autowired
    private CurrentAccountRepository currentAccountrepo;

    @Autowired
    private JavaMailSender javaMailSender;
    
    public boolean authenticate(String username, String password) {
    	AdminEntity admin = adminRepository.findByUsername(username);
    	 System.out.println("login success");
        return admin != null && admin.getPassword().equals(password);
       
    }

    public List<Registration> getPendingApprovals() {
        return registrationRepository.findAll().stream()
                .filter(reg -> !reg.getisApproved())
                .toList();
    }
//    public List<Registration> getalreadyExist(){
//    	return registrationRepository.findAll().stream().filter(reg -> !reg.getAlready_exist()).toList();
//    }
//    public List<SavingsAccount> getExist(){
//    	return  savingsAccountRepo.findAll().stream().filter(reg -> !reg.getIsalreadyexist()).toList();
//    }
    
    
    
//  
    
    public List<BankSlip> getPending() {
        return bankrepo.findAll();
    }
    public List<Object[]> getPendingCheques() {
        return adminRepository.getpending();
    }
    public List<Object[]> getbankslips() {
        return adminRepository.getbankslips();
    }
    
    public List<SavingsAccount> getPendingCounts(){
    	return  savingsAccountRepo.findAll().stream().filter(reg ->reg.getCount()>=3).toList();
    }

    public Registration approveUser(Long userId) {
        Registration registration = registrationRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        registration.setApproved(true);
        Registration approvedRegistration = registrationRepository.save(registration);
        SavingsAccount savings=savingsAccountRepo.findByRegistration(registration);
        
        sendApprovalEmail(approvedRegistration,savings);
        
        return approvedRegistration;
    }
    public Registration approveUsers(Long userId) {
        Registration registration = registrationRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        registration.setApproved(true);
        Registration approvedRegistration = registrationRepository.save(registration);
        //SavingsAccount savings=savingsAccountRepo.findByRegistration(registration);
        CurrentAccount current = currentAccountrepo.findByUserId(userId);
        sendApprovalEmails(approvedRegistration,current);
        
        return approvedRegistration;
    }
    
    
    
    
    

    private void sendApprovalEmail(Registration registration,SavingsAccount savings) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(registration.getEmail());
        mailMessage.setSubject("Account Approved");
        mailMessage.setText("Dear " + registration.getName() + ",\n\n" +
                "Your account has been approved. Your account ID is: " + savings.getAccountId() + "\n" +
                "Account Type: " + registration.getAccountType() + "\n" +
                "Phone: " + registration.getPhone() + "\n" +
                "City: " + registration.getCity() + "\n" +
                "State: " + registration.getState() + "\n\n" +
                "Thank you for registering with us.\n\nBest regards,\nYour Company Name");
        
        javaMailSender.send(mailMessage);
    }
    private void sendApprovalEmails(Registration registration,CurrentAccount current) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(registration.getEmail());
        mailMessage.setSubject("Account Approved");
        mailMessage.setText("Dear " + registration.getName() + ",\n\n" +
                "Your account has been approved. Your account ID is: " + current.getAccountId() + "\n" +
                "Account Type: " + registration.getAccountType() + "\n" +
                "Phone: " + registration.getPhone() + "\n" +
                "City: " + registration.getCity() + "\n" +
                "State: " + registration.getState() + "\n\n" +
                "Thank you for registering with us.\n\nBest regards,\nYour Company Name");
        
        javaMailSender.send(mailMessage);
    }
    
    
    
//    public Registration approveUsers(long userId) {
//        Registration registration = registrationRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        registration.setApproved(true);
//        Registration approvedRegistration = registrationRepository.save(registration);
//        CurrentAccount current=currentAccountrepo.findByRegistration(registration);
//        
//        sendApprovalEmails(approvedRegistration,current);
//        
//        return approvedRegistration;
//    }
//    
    
    

	public AdminEntity registerAdmin(AdminEntity admin) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int adminSetCount0(Long accountId) {
		Optional<SavingsAccount> op2=savingsAccountRepo.findById(accountId);
		if(op2.isPresent()) {
			SavingsAccount savingsAccount=op2.get();
			savingsAccount.setCount(0);
			savingsAccountRepo.save(savingsAccount);
			return 1;
		}
		else {
			return 0;
		}
	}
	public int adminSetCount1(Long accountId) {
		Optional<CurrentAccount> op2=currentAccountrepo.findById(accountId);
		if(op2.isPresent()) {
			CurrentAccount currentAccount=op2.get();
			currentAccount.setCount(0);
			currentAccountrepo.save(currentAccount);
			return 1;
		}
		else {
			return 0;
		}
	}
}