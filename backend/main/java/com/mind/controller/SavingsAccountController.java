
package com.mind.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mind.dto.AccountDTO;
import com.mind.entity.AdminEntity;
import com.mind.entity.Registration;
import com.mind.entity.SavingsAccount;
import com.mind.entity.SavingsTransactions;
import com.mind.entity.Transaction;
import com.mind.entity.TransactionRequest;
import com.mind.exception.AccountNotFoundException;
import com.mind.repository.CurrentAccountRepository;
import com.mind.service.SavingsAccountService;
import com.mind.service.SavingsTransactionsService;


@CrossOrigin(origins="http://localhost:5173") 
@RestController
@RequestMapping("/api/savings")
public class SavingsAccountController {
    @Autowired
    private SavingsAccountService savingsAccountService;
    @Autowired
    private SavingsTransactionsService transService;
    @Autowired
    private CurrentAccountRepository currentAccountRepository;
    @GetMapping("/{accountId}")
    public SavingsAccount getSavingsAccount(@PathVariable Long accountId) {
        return savingsAccountService.getSavingsAccount(accountId);
    }
    /*

    @PostMapping("/withdraw")
    public boolean withdraw(@RequestParam("accountId") Integer accountId, @RequestParam Double amount) {
        return savingsAccountService.withdraws(accountId, amount);
    }

    @PostMapping("/deposit")
    public void deposit(@RequestParam(value ="accountId", required = true) Integer accountId, @RequestParam Double amount) {
        savingsAccountService.deposit(accountId, amount);
    }
    */
    
    
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody SavingsAccount admin) {
    	int status=savingsAccountService.authenticate(admin.getAccountId(), admin.getPassword());
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
    
    @PostMapping("/deposit2")
    public void deposit2(@RequestBody SavingsAccount savings) {
        savingsAccountService.deposit(savings.getAccountId(),savings.getPassword(), savings.getBalance());
    }
    @PostMapping("/withdraw2")
    public void withdraw2(@RequestBody SavingsAccount savings) {
        savingsAccountService.withdraw2(savings.getAccountId(), savings.getPassword(), savings.getBalance());
    }
    
    
    @GetMapping("/listofdetails/{accountId}")
    public ResponseEntity<Optional<SavingsAccount>> getUserDetails(@PathVariable Long accountId){
    	Optional<SavingsAccount> userlist = savingsAccountService.getUserDetails(accountId);
    	return ResponseEntity.ok(userlist);
    }
    
    
    @GetMapping("/ListAccounts/{userId")
    public ResponseEntity<Object> listOfAccounts(@PathVariable("userId") Long userId){
    	Optional<SavingsAccount> userlist = savingsAccountService.getUserDetails(userId);
    	return ResponseEntity.ok(userlist); 
    }
    
//    @GetMapping("/listacc/{userId}")
//    public ResponseEntity<Object> Listofacc(@PathVariable("userId") int userId){
//    	int accountId=(int) savingsAccountService.Listofaccounts(userId);
//    	return ResponseEntity.status(201).body(accountId);
//    }
//    
    @GetMapping("/listacc/{userId}")
    public ResponseEntity<List<AccountDTO>> listOfAcc(@PathVariable("userId") int userId) {
        List<AccountDTO> accountDetails = savingsAccountService.listOfAccounts(userId);
        if (accountDetails.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 if no accounts found
        }
        return ResponseEntity.ok(accountDetails); // Return 200 with the list of account details
    }
    
    
    
    @PostMapping("/withdraw/{accountId}/{password}")
    public ResponseEntity<Object> withdraw(@PathVariable Long accountId,@PathVariable String password, @RequestBody TransactionRequest transactionRequest) {
        try {
            SavingsAccount account = savingsAccountService.withdraw1(accountId,password, transactionRequest.getAmount());
            return ResponseEntity.ok(account);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @PostMapping("/epay/{accountId}/{password}")
    public ResponseEntity<Object> epay(@PathVariable Long accountId, @PathVariable String password, @RequestBody TransactionRequest transactionRequest) {
        try {
            SavingsAccount account = savingsAccountService.Epay(accountId,transactionRequest.getAmount(), password);
            return ResponseEntity.ok(account);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    
    
   
    
    

    @PostMapping("/transfer/{sourceAccountId}/{targetAccountId}/{amount}/{password}")
    public ResponseEntity<Object> transfer(@PathVariable("sourceAccountId") Long sourceAccountId,@PathVariable("targetAccountId") Long targetAccountId, @PathVariable("amount") Double amount,@PathVariable("password") String password) {
        int status=savingsAccountService.transfer(sourceAccountId, targetAccountId, amount,password);
        if(status==2) {
        	return ResponseEntity.status(201).body("Amount Transfer SuccessFully");
        	
        }else if(status==1) {
        	return ResponseEntity.status(201).body("Insufficient amount");
        }
        else {
        	return ResponseEntity.status(201).body("account is not found");
        }
    }
    
    @GetMapping("/userBalance/{accountId}")
    public ResponseEntity<Object> userBalance(@PathVariable("accountId") Long accountId){
    	long balance=savingsAccountService.userDetails(accountId);
    	return ResponseEntity.status(201).body(balance);
    }
    
    
    @GetMapping("/userName/{accountId}")
    public ResponseEntity<Object> userName(@PathVariable("accountId") Long accountId){
    	String name=savingsAccountService.userFirstname(accountId);
    	return ResponseEntity.status(201).body(name);
    }
    
    
    @GetMapping("/viewtrans/{accountId}")
    public ResponseEntity<Object> lastTransaction(@PathVariable("accountId" )Long accountId){
    	List<SavingsTransactions> list=transService.viewLastFiveTransaction(accountId);
    	return  ResponseEntity.status(201).body(list);
    }
    
   /* @PostMapping("/store/transaction")
    public  ResponseEntity<Object>storeTrans(@RequestBody SavingsTransactions savingsTransactions){
		SavingsTransactions sat=transService.storeTransactions(savingsTransactions);
		return ResponseEntity.status(201).body("Success");
    	
    }
    */
    @PostMapping("/store/transaction/{accountId}/{TargetAccount}/{amount}/{description}")
    public  ResponseEntity<Object>storeTrans(@PathVariable ("accountId" )Long accountId,@PathVariable("TargetAccount") Long TargetAccount,@PathVariable("amount")double amount,@PathVariable("description")String description){
		SavingsTransactions sat=transService.storeTransactions(accountId, TargetAccount, amount, description);
		return ResponseEntity.status(201).body("Success");
    	
   }
//    @GetMapping("/multiple-accounts")
//    public List<SavingsAccount> getAccountsForUsersWithMultipleAccounts() {
//        return savingsAccountService.getAccountsForUsersWithMultipleAccounts();
//    }
    //int userId,double balance,String password
    @PostMapping("/existingSavings/{userId}/{balance}/{password}/{email}")
    public ResponseEntity<Object> storeExistingSavings(@PathVariable("userId")long userId,@PathVariable("balance")double balance,@PathVariable("password")String password,@PathVariable("email") String email)throws AccountNotFoundException{
    	
    	try {
    		savingsAccountService.existSavingAccount(userId, balance, password,email);
    		return ResponseEntity.status(201).body("Success");
    	}catch(AccountNotFoundException e) {
    		return ResponseEntity.status(201).body("failed");
    	}
    }
    
    @PostMapping("/existingCurrent/{userId}/{balance}/{password}/{email}")
    public ResponseEntity<Object> storeExistingCurrent(@PathVariable("userId")long userId,@PathVariable("balance")double balance,@PathVariable("password")String password,@PathVariable("email") String email)throws AccountNotFoundException{
    	
    	try {
    		savingsAccountService.existCurrentAccount(userId, balance, password, email);
    		return ResponseEntity.status(201).body("Success");
    	}catch(AccountNotFoundException e) {
    		return ResponseEntity.status(201).body("failed");
    	}
    }
    
    @PostMapping
    public ResponseEntity<SavingsAccount> createAccount(@RequestBody SavingsAccount account) {
        SavingsAccount createdAccount = savingsAccountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }
//    @PostMapping("/existingSavings/{userId}/{balance}/{password}/{email}")
//    public ResponseEntity<Object> storeExistingSavings(@PathVariable("userId")int userId,@PathVariable("balance")double balance,@PathVariable("password")String password,@PathVariable("email") String email)throws AccountNotFoundException{
//    	
//    	try {
//    		savingsAccountService.existSavingAccount2(userId, balance, password, email);
//    		return ResponseEntity.status(201).body("Success");
//    	}catch(AccountNotFoundException e) {
//    		return ResponseEntity.status(201).body("failed");
//    	}
//    }
    
    
    
    
    
}



