package com.mind.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mind.dto.AccountDTO;
import com.mind.entity.CurrentAccount;
import com.mind.entity.CurrentTransactions;
import com.mind.entity.EpayRequest;
import com.mind.entity.EpayResponse;
import com.mind.entity.SavingsAccount;
import com.mind.entity.TransactionRequest;
import com.mind.exception.AccountNotFoundException;
import com.mind.service.CurrentAccountService;
import com.mind.service.CurrentTransactionService;


@CrossOrigin(origins="http://localhost:5173") 
@RestController
@RequestMapping("/currentaccount")
public class CurrentAccountController {

    @Autowired
    private CurrentAccountService service;
    
    @Autowired
    private CurrentTransactionService currentTransactionService;

    @PostMapping
    public CurrentAccount createAccount(@RequestBody CurrentAccount account) {
        return service.createAccount(account);
    }

    @GetMapping("/{id}")
    public CurrentAccount getAccount(@PathVariable Long id) {
        return service.getAccount(id);
    }

    @PutMapping
    public CurrentAccount updateAccount(@RequestBody CurrentAccount account) {
        return service.updateAccount(account);
    }

    @PostMapping("/{id}/enableOverdraft")
    public CurrentAccount enableOverdraft(@PathVariable Long id, @RequestBody TransactionRequest transactionRequest) {
        return service.enableOrDisableOverdraft(id, transactionRequest.isEnable());
    }
    
    @PostMapping("/{id}/{password}/deposit")
    public ResponseEntity<Object> deposit(@PathVariable Long id, @PathVariable String password, @RequestBody TransactionRequest transactionRequest) {
        try {
            CurrentAccount account = service.deposit(id,password, transactionRequest);
            return ResponseEntity.ok(account);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @PostMapping("/{id}/{password}/withdraw")
    public ResponseEntity<Object> withdraw(@PathVariable long id, @PathVariable String password ,@RequestBody TransactionRequest transactionRequest) {
        try {
            CurrentAccount account = service.withdraw(id, password ,transactionRequest);
            return ResponseEntity.ok(account);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody CurrentAccount admin) {
    	int status=service.authenticate(admin.getAccountId(), admin.getPassword());
    	if(status==0) {
    		return ResponseEntity.status(201).body("Login successfull");
    	}
    	else if(status==1) {
    		return ResponseEntity.status(404).body("Login failed");	
    	}
    	else if(status==2) {
    		return ResponseEntity.status(209).body("account blocked");	
    	}
		return null;
    }
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    @PostMapping("/transfer/{sourceAccountId}/{targetAccountId}/{amount}/{password}")
    public ResponseEntity<Object> transfer(@PathVariable("sourceAccountId") Long sourceAccountId,@PathVariable("targetAccountId") Long targetAccountId, @PathVariable("amount") Double amount,@PathVariable("password") String password) {
        int status=service.transfer(sourceAccountId, targetAccountId, amount,password);
        if(status==2) {
        	return ResponseEntity.status(201).body("Amount Transfer SuccessFully");
        	
        }else if(status==1) {
        	return ResponseEntity.status(201).body("Insufficient amount");
        }
        else {
        	return ResponseEntity.status(201).body("account is not found");
        }
    }
    
    
    @PostMapping("/{accountId}/epay")
    public ResponseEntity<?> epay(
            @PathVariable Long accountId,
            @RequestBody EpayRequest epayRequest) {
        
        try {
           
            CurrentAccount updatedAccount = service.Epay(accountId, epayRequest.getAmount(), epayRequest.getPassword());
            EpayResponse epayResponse = new EpayResponse();
            epayResponse.setBalance(updatedAccount.getBalance());
            epayResponse.setOverdraftLimit(updatedAccount.getOverdraftLimit());
            
           
            return ResponseEntity.ok(epayResponse);
        } catch (IllegalArgumentException e) {
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
           
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request.");
        }
    }
    
    @GetMapping("/listofdetail/{accountId}")
    public ResponseEntity<Optional<CurrentAccount>> getUserDetail(@PathVariable Long accountId){
    	Optional<CurrentAccount> userlist = service.getUserDetails(accountId);
    	return ResponseEntity.ok(userlist);
    }
    
    
    
    
    @GetMapping("/userBalance/{accountId}")
    public ResponseEntity<Object> userBalance(@PathVariable("accountId") Long accountId){
    	long balance=service.userDetails(accountId);
    	return ResponseEntity.status(201).body(balance);
    }
    
    @GetMapping("/userName/{accountId}")
    public ResponseEntity<Object> userName(@PathVariable("accountId") Long accountId){
    	String name=service.userFirstname(accountId);
    	return ResponseEntity.status(201).body(name);
    }
    
    
    
    @GetMapping("/ListAccounts/{userId")
    public ResponseEntity<Object> listOfAccounts(@PathVariable("userId") Long userId){
    	Optional<CurrentAccount> userlist = service.getUserDetails(userId);
    	return ResponseEntity.ok(userlist); 
    }
    
    
    @GetMapping("/listacc/{userId}")
    public ResponseEntity<List<AccountDTO>> listOfAcc(@PathVariable("userId") int userId) {
        List<AccountDTO> accountDetails = service.listOfAccounts(userId);
        if (accountDetails.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 if no accounts found
        }
        return ResponseEntity.ok(accountDetails); // Return 200 with the list of account details
    }
    
    @GetMapping("/viewTransactionCurrent/{accountId}")
    public ResponseEntity<Object> viewTransactionCurrent(@PathVariable("accountId") long accountId){
    	List<CurrentTransactions> list=currentTransactionService.viewTransactionCurrent(accountId);
    	return ResponseEntity.status(201).body(list);
    }
    
   
    
}
    
    
    
    
    
    
    
    


