package com.mind.service;



import com.mind.entity.SavingsAccount;
import com.mind.repository.SavingsAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private SavingsAccountRepository savingsAccountRepository;

    public boolean payElectricityBill(Long accountId, Double amount) {
        SavingsAccount account = savingsAccountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        return account.payBill(amount);
    }

    // Similar methods can be created for other types of bills
}
