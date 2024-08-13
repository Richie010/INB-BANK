package com.mind.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.mind.entity.BankSlip;



public interface BankSlipRepo extends JpaRepository<BankSlip, Long> {

}
