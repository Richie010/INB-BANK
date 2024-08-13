package com.mind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mind.dto.AccountRequest;
import com.mind.dto.MultiAccountRequest;
import com.mind.entity.AdminEntity;
import com.mind.entity.Registration;
import com.mind.exception.AccountNotFoundException;
import com.mind.service.RegistrationService;

import java.util.List;

@CrossOrigin(origins="http://localhost:5173")
@RestController
@RequestMapping("/api/registration")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public Registration registerUser(@RequestBody Registration registration) {
        return registrationService.registerUser(registration);
    }
    @PostMapping("/login")
    public boolean login(@RequestBody Registration register) {
    	return registrationService.authenticate(register.getEmail(), register.getPassword());
		
    	
    }
   
    @PostMapping("/createExistingRegister/{userId}/{accountType}/{email}/{balance}/{password}")
    public ResponseEntity<Object> createExistingRegister(@PathVariable("userId") long userId,@PathVariable("accountType") String accountType,@PathVariable("email") String email,@PathVariable("balance") double balance,@PathVariable("password") String password)throws AccountNotFoundException{
    	try {
    		registrationService.existRegisterAccount(userId, balance, password, email, accountType);
    		return ResponseEntity.status(201).body("success");
    	}catch(AccountNotFoundException e) {
    		return ResponseEntity.status(201).body(e.getMessage());
    	}
    }

    
    
    
    
    
    @PostMapping("/existingRegister/{userId}/{balance}/{password}/{email}/{accountType}")
    public ResponseEntity<Object> storeExistingRegister(@PathVariable("userId")int userId,@PathVariable("balance")double balance,@PathVariable("password")String password,@PathVariable("email") String email,@PathVariable("accountType") String accountType)throws AccountNotFoundException{
    	
    	try {
    		registrationService.existRegisterAccount(userId, balance, password,email,accountType);
    		return ResponseEntity.status(201).body("Success");
    	}catch(AccountNotFoundException e) {
    		return ResponseEntity.status(201).body("failed");
    	}
    }
    
    
    @GetMapping("/pending")
    public List<Registration> getPendingRegistrations() {
        return registrationService.getPendingRegistrations();
    }

    @PostMapping("/approve/{userId}")
    public Registration approveUser(@PathVariable Long userId) {
        return registrationService.approveUser(userId);
    }
}