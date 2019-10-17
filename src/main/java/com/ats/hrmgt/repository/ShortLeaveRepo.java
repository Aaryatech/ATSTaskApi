package com.ats.hrmgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.hrmgt.model.ShortLeave;

public interface ShortLeaveRepo extends JpaRepository<ShortLeave, Integer> {
	
	

}
