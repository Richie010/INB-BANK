package com.mind.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mind.entity.BankSlip;
import com.mind.service.BankSlipService;


@CrossOrigin(origins="http://localhost:5173")
@RestController
@RequestMapping("/bankSlips")
public class BankSlipController {
	
	@Autowired
	private BankSlipService bankSlipService;
	
	@PostMapping("/create")
    public ResponseEntity<String> createBankSlip(@RequestBody BankSlip bankSlip) {
        try {
            BankSlip createdBankSlip = bankSlipService.createBankSlip(bankSlip);
            return ResponseEntity.status(HttpStatus.CREATED).body("Bank slip created successfully with ID: " + createdBankSlip.getBankSlipId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
    @PostMapping("/process/{chequeId}/{bankSlipId}")
    public ResponseEntity<String> processCheque(@PathVariable Long chequeId, @PathVariable Long bankSlipId) {
        try {
            bankSlipService.processCheque(chequeId, bankSlipId);
            return ResponseEntity.status(HttpStatus.OK).body("Cheque processed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

}
