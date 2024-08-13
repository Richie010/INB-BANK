package com.mind.controller;

import com.mind.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/electricity")
    public ResponseEntity<String> payElectricityBill(@PathVariable Long accountId, @PathVariable Double amount) {
        boolean success = paymentService.payElectricityBill(accountId, amount);
        if (success) {
            return ResponseEntity.ok("Payment successful");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient balance");
        }
    }

    // Similar endpoints can be created for other types of bills
}