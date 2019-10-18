package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmpWorkHrsRep;
 
public interface EmpWorkHrsRepRepo  extends JpaRepository<EmpWorkHrsRep, Integer>{
	
	
	@Query(value = "select emp_info.emp_id,\n" + 
			"\n" + 
			"CONCAT(FLOOR( Sum(t_work_log.work_hrs)/60),\n" + 
			"            ':',\n" + 
			"            LPAD(MOD(Sum(t_work_log.work_hrs),\n" + 
			"            60),\n" + 
			"            2,\n" + 
			"            '0'))  as emp_bud_hr, emp_info.emp_fname,emp_info.emp_sname from t_work_log,emp_info WHERE t_work_log.emp_id=emp_info.emp_id  AND t_work_log.log_type=2 AND t_work_log.del_status=1  AND t_work_log.work_date BETWEEN :fromDate AND :toDate AND t_work_log.emp_id IN (:empId) GROUP BY t_work_log.emp_id ", nativeQuery = true)
	List<EmpWorkHrsRep> getGetEmpWorkLog(@Param("empId") List<String> empIdList,@Param("fromDate") String fromDate,@Param("toDate") String toDate);
	
	@Query(value = "select emp_info.emp_id,\n" + 
			"\n" + 
			"CONCAT(FLOOR( Sum(t_work_log.work_hrs)/60),\n" + 
			"            ':',\n" + 
			"            LPAD(MOD(Sum(t_work_log.work_hrs),\n" + 
			"            60),\n" + 
			"            2,\n" + 
			"            '0'))  as emp_bud_hr, emp_info.emp_fname,emp_info.emp_sname from t_work_log,emp_info WHERE t_work_log.emp_id=emp_info.emp_id  AND t_work_log.log_type=2 AND t_work_log.del_status=1  AND t_work_log.work_date BETWEEN :fromDate AND :toDate  GROUP BY t_work_log.emp_id ", nativeQuery = true)
	List<EmpWorkHrsRep> getGetEmpWorkLogAll(@Param("fromDate") String fromDate,@Param("toDate") String toDate);

}
