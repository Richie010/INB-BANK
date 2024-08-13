package com.mind.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mind.entity.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
	
	List<Registration> findByisApprovedFalse();
	Registration findByemail(String email);
	Registration findByUserId(long userId);
	
	 Registration findByUserId(Long userId);
	
	
}

