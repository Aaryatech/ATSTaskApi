package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.GetEmpLogGrpByDate;

public interface GetEmpLogGrpByDateRepo extends JpaRepository<GetEmpLogGrpByDate, Integer> {
	
	
	@Query(value = "  SELECT" + 
			"      UUID() as id," + 
			"         t_work_log.emp_id," + 
			"        t_work_log.work_date," + 
			"        emp_info.emp_fname," + 
			"        emp_info.emp_sname," + 
			"        group_concat(distinct project_header.project_title) as project_title," + 
			"       COALESCE(         (             CONCAT(                 FLOOR( sum(t_work_log.work_hrs )/ 60)," + 
			"        ':'," + 
			"        LPAD(MOD(sum(t_work_log.work_hrs)," + 
			"        60)," + 
			"        2," + 
			"        '0')             )         )," + 
			"        0     )  AS work_hrs " + 
			"    FROM" + 
			"        emp_info," + 
			"        t_work_log ,project_header" + 
			"    WHERE" + 
			"        emp_info.emp_id=t_work_log.emp_id  and t_work_log.project_id=project_header.project_id " + 
			"        AND t_work_log.del_status=1 " + 
			"        AND t_work_log.is_active=1 " + 
			"        and t_work_log.work_date between :fromDate and  :toDate   " + 
			"        group by t_work_log.emp_id,t_work_log.work_date ", nativeQuery = true)
	List<GetEmpLogGrpByDate> getWorkLogAdmOnload(@Param("fromDate") String fromDate,@Param("toDate") String toDate);
	
	
	@Query(value = "  SELECT" + 
			"      UUID() as id," + 
			"         t_work_log.emp_id," + 
			"        t_work_log.work_date," + 
			"        emp_info.emp_fname," + 
			"        emp_info.emp_sname," + 
			"        group_concat(distinct project_header.project_title) as project_title," + 
			"        COALESCE(         (             CONCAT(                 FLOOR(sum(t_work_log.work_hrs) / 60)," + 
			"        ':'," + 
			"        LPAD(MOD(sum(t_work_log.work_hrs)," + 
			"        60)," + 
			"        2," + 
			"        '0')             )         )," + 
			"        0     ) AS work_hrs " + 
			"    FROM" + 
			"        emp_info," + 
			"        t_work_log ,project_header" + 
			"    WHERE" + 
			"        emp_info.emp_id=t_work_log.emp_id  and t_work_log.project_id=project_header.project_id " + 
			"        AND t_work_log.del_status=1 " + 
			"        AND t_work_log.is_active=1 " + 
			"        and t_work_log.work_date between :fromDate and  :toDate and " + 
			"        t_work_log.emp_id IN (:empIdList)" + 
			"        group by t_work_log.emp_id,t_work_log.work_date", nativeQuery = true)
	List<GetEmpLogGrpByDate> getWorkLogAdmBySearch(@Param("fromDate") String fromDate,@Param("toDate") String toDate,
			@Param("empIdList") List<String> empIdList);

}
