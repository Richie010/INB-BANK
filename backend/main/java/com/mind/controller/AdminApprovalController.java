package com.mind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mind.dto.ChequeBankSlipDTO;
import com.mind.entity.AdminEntity;
import com.mind.entity.BankSlip;
import com.mind.entity.Cheque;
import com.mind.entity.Registration;
import com.mind.entity.SavingsAccount;
import com.mind.repository.RegistrationRepository;
import com.mind.service.AdminApprovalService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins="http://localhost:5173")
@RestController
@RequestMapping("/api/admin")
public class AdminApprovalController {
    @Autowired
    private AdminApprovalService adminApprovalService;
    @Autowired
    private RegistrationRepository registrationRepository;
    
    @PostMapping("/login")
    public boolean login(@RequestBody AdminEntity admin) {
    	return adminApprovalService.authenticate(admin.getUsername(), admin.getPassword());
		
    	
    }
    @GetMapping("/chequepending")
    public List<Object[]> getPendingCheques() {
        return adminApprovalService.getPendingCheques();
    }
    
    @GetMapping("/bankslipspending")
    public List<Object[]> getbankslips() {
        return adminApprovalService.getbankslips();
    }
    
    
    /*
    @GetMapping("/pending-approvals")
    public ResponseEntity<List<Registration>> getPendingApprovals() {
        List<Registration> allRegistrations = registrationRepository.findAll();
        List<Registration> pendingApprovals = allRegistrations.stream()
                .filter(registration -> !registration.getisApproved())
                .collect(Collectors.toList());

        return ResponseEntity.ok(pendingApprovals);
    }*/
    
    

    
    @GetMapping("/pending")
    public ResponseEntity<List<Registration>> getPendingApproval() {
    	List<Registration> pendingApprovals = adminApprovalService.getPendingApprovals();
    	return ResponseEntity.ok(pendingApprovals);
    } 
    
    
    @GetMapping("/pendings")
    public ResponseEntity<List<BankSlip>> getPendingApp() {
    	List<BankSlip> pendingApprovals = adminApprovalService.getPending();
    	return ResponseEntity.ok(pendingApprovals);
    } 
    
    
    
    
    
//    @GetMapping("/pendings")
//    public ResponseEntity<List<SavingsAccount>> getExistPending() {
//    	List<SavingsAccount> pendingApprovals = adminApprovalService. getExist();
//    	return ResponseEntity.ok(pendingApprovals);
//    } 
//    
    
    
    
//    @GetMapping("/pendings")
//    public ResponseEntity<List<Registration>> getAlreadyExist() {
//    	List<Registration> pendingApprovals = adminApprovalService.getalreadyExist();
//    	return ResponseEntity.ok(pendingApprovals);
//    } 
//    
    
    @GetMapping("/loginpending")
    public ResponseEntity<List<SavingsAccount>> getPendingCount(){
    	List<SavingsAccount> pendingBlocked = adminApprovalService.getPendingCounts();
    	return ResponseEntity.ok(pendingBlocked);
    }

    @GetMapping("/approvals")
    public List<Registration> getPendingApprovals() {
        return adminApprovalService.getPendingApprovals();
    }
    @PostMapping("/register")
    public AdminEntity registerAdmin(@RequestBody AdminEntity admin) {
    	return adminApprovalService.registerAdmin(admin);
    }

    @PutMapping("/approve/{userId}")
    public Registration approveUser(@PathVariable Long userId) {
        return adminApprovalService.approveUser(userId);
    }
    
    @PutMapping("/approves/{userId}")
    public Registration approveUsers(@PathVariable Long userId) {
        return adminApprovalService.approveUsers(userId);
    }
    @PutMapping("/approveCount0/{accountId}")
    public ResponseEntity<String> adminSetCount0(@PathVariable("accountId") Long accountId) {
        if(adminApprovalService.adminSetCount0(accountId)==1) {
        	return ResponseEntity.status(200).body("Done");
        }
		return ResponseEntity.status(404).body("Not Done");
        
    }
    @PutMapping("/approveCount1/{accountId}")
    public ResponseEntity<String> adminSetCount1(@PathVariable("accountId") Long accountId) {
        if(adminApprovalService.adminSetCount1(accountId)==1) {
        	return ResponseEntity.status(200).body("Done");
        }
		return ResponseEntity.status(404).body("Not Done");
        
    }
}