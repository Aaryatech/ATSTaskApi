package com.ats.hrmgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.hrmgt.model.WorkLog;

public interface WorkLogRepo extends JpaRepository<WorkLog, Integer> {

}
