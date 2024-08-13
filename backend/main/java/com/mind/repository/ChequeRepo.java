package com.mind.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.mind.entity.Cheque;



public interface ChequeRepo extends JpaRepository<Cheque, Long> {

}