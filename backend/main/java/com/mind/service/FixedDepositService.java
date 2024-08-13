package com.mind.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mind.entity.DepositRate;
import com.mind.entity.FixedDeposit;
import com.mind.repository.DepositRateRepository;
import com.mind.repository.FixedDepositRepository;

@Service
public class FixedDepositService {

    @Autowired
    private DepositRateRepository depositRateRepository;

    @Autowired
    private FixedDepositRepository fixedDepositRepository;

   
    public FixedDeposit createFixedDeposit(int accountId, double depositAmount, int duration) throws Exception {
       
        DepositRate depositRate = depositRateRepository.findById(duration)
                .orElseThrow(() -> new Exception("Invalid duration selected"));

        double interestRate = depositRate.getInterestRate();

     
        double interestAmount = calculateInterest(depositAmount, interestRate, duration);

       
        FixedDeposit fixedDeposit = new FixedDeposit();
        fixedDeposit.setAccountId(accountId);
        fixedDeposit.setDepositAmount(depositAmount);
        fixedDeposit.setDuration(duration);
        fixedDeposit.setInterestRate(interestRate);
        //fixedDeposit.setInterestAmount(interestAmount);

        
        fixedDepositRepository.save(fixedDeposit);

       
        return fixedDeposit;
    }

   
    private double calculateInterest(double depositAmount, double interestRate, int duration) {
      
        double rateDecimal = interestRate / 100;

      
        double timeInYears = duration / 12.0;

     
        return depositAmount * rateDecimal * timeInYears;
    }


    public FixedDeposit createFixedDeposit(FixedDeposit fixeddepo) {
       
        int duration = fixeddepo.getDuration();
        if (duration != 12 && duration != 24 && duration != 36) {
            throw new IllegalArgumentException("Invalid duration. Supported durations are 12, 24, and 36 months.");
        }

       
        FixedDeposit depositRate = fixedDepositRepository.save(fixeddepo);

       
        double interestAmount = calculateInterest(depositRate.getDepositAmount(), depositRate.getInterestRate(), depositRate.getDuration());

        
        depositRate.setInterestRate(interestAmount);
        return fixedDepositRepository.save(depositRate); // Save the updated object
    }
}
