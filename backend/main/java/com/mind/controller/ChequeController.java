package com.mind.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mind.entity.Cheque;
import com.mind.service.ChequeService;



@CrossOrigin(origins="http://localhost:5173")
@RestController
@RequestMapping("/cheques")
public class ChequeController {
	
	@Autowired
	private ChequeService chequeService;
	
	@PostMapping("/createcheque")
	public ResponseEntity<String> storeChequeDetails(@RequestBody Cheque cheque){
		try {
			Cheque savedCheque=chequeService.storeChequeDetails(cheque);
			return ResponseEntity.status(HttpStatus.CREATED).body("Cheque stored successfully with ID: " + savedCheque.getChequeId());
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to Create");
		}
	}
	
	

}

