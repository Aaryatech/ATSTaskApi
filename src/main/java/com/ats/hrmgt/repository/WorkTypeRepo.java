package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.Company;
import com.ats.hrmgt.model.WorkType;

public interface WorkTypeRepo  extends JpaRepository<WorkType, Integer>{

	
	
	@Transactional
	@Modifying
	@Query("update WorkType set del_status=0  WHERE work_type_id=:workTypeId")
	int deleteWorkType(@Param("workTypeId") int workTypeId);

	WorkType findByWorkTypeIdAndDelStatus(int workTypeId, int i);

	List<WorkType> findByDelStatusOrderByWorkTypeIdDesc(int i);

}
