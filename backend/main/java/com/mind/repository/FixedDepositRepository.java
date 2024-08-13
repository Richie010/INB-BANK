package com.mind.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mind.entity.FixedDeposit;



public interface FixedDepositRepository extends JpaRepository<FixedDeposit,Integer> {

}
