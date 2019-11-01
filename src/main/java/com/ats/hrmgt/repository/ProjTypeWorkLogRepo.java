package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.ProjTypeWorkLog;

public interface ProjTypeWorkLogRepo extends JpaRepository<ProjTypeWorkLog , Integer> {

	@Query(value="SELECT  \n" + 
			"        t_work_log.work_log_id,\n" + 
			"        t_work_log.project_id,\n" + 
			"        t_work_log.work_type_id,\n" + 
			"        project_header.project_title,\n" + 
			"        t_work_type.work_type_name,\n" + 
			"        CONCAT(FLOOR(SUM(t_work_log.work_hrs) / 60),':', LPAD(MOD(SUM(t_work_log.work_hrs), 60), 2,'0')) AS work_hrs \n" + 
			"\n" + 
			"FROM    \n" + 
			"        t_work_log,\n" + 
			"        t_work_type,\n" + 
			"        project_header\n" + 
			"WHERE   \n" + 
			"        t_work_log.project_id=project_header.project_id \n" + 
			"        AND t_work_log.work_type_id=t_work_type.work_type_id\n" + 
			"        AND t_work_log.work_date BETWEEN :fromDate AND :toDate\n" + 
			"        AND t_work_log.del_status=1 \n" + 
			"        AND t_work_log.is_active=1 \n" + 
			"        GROUP BY t_work_log.work_type_id, t_work_log.project_id",nativeQuery=true)
	List<ProjTypeWorkLog> getProjTypeWorkLogAdmBySearch(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

}
