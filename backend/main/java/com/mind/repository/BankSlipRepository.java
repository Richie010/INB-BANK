package com.mind.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mind.entity.BankSlip;


@Repository
public interface BankSlipRepository extends JpaRepository<BankSlip, Long> {
    // JpaRepository provides CRUD operations by default
}

