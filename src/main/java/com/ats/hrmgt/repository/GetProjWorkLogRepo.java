package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.GetEmpWorkLog;
import com.ats.hrmgt.model.GetProjWorkLog;

public interface GetProjWorkLogRepo extends JpaRepository<GetProjWorkLog, Integer> {

	//Mahendra 
		//01-11-2019
		@Query(value="SELECT\n" + 
				"        t_work_log.work_log_id,\n" + 
				"        t_work_log.project_id,\n" + 
				"        project_header.project_title,\n" + 
				"        CONCAT( FLOOR(SUM(t_work_log.work_hrs) / 60),':',LPAD(MOD(SUM(t_work_log.work_hrs), 60),2, '0') )  AS work_hrs  \n" + 
				"    FROM\n" + 
				"        project_header,\n" + 
				"        t_work_log \n" + 
				"    WHERE\n" + 
				"         \n" + 
				"         t_work_log.project_id=project_header.project_id \n" + 
				"        AND t_work_log.del_status=1 \n" + 
				"        AND t_work_log.is_active=1  \n" + 
				"        AND t_work_log.work_date BETWEEN :fromDate AND :toDate\n" + 
				"        and t_work_log.project_id IN (:projIdList)\n" + 
				"        GROUP BY t_work_log.project_id",nativeQuery=true)
		List<GetProjWorkLog> getProjWorkLogAdmBySearch(@Param("fromDate") String fromDate,@Param("toDate") String toDate,@Param("projIdList") List<String> projIdList);

		@Query(value="SELECT\n" + 
				"        t_work_log.work_log_id,\n" + 
				"        t_work_log.project_id,\n" + 
				"        project_header.project_title,\n" + 
				"        CONCAT( FLOOR(SUM(t_work_log.work_hrs) / 60),':',LPAD(MOD(SUM(t_work_log.work_hrs), 60),2, '0') )  AS work_hrs  \n" + 
				"    FROM\n" + 
				"        project_header,\n" + 
				"        t_work_log \n" + 
				"    WHERE\n" + 
				"         \n" + 
				"         t_work_log.project_id=project_header.project_id \n" + 
				"        AND t_work_log.del_status=1 \n" + 
				"        AND t_work_log.is_active=1  \n" + 
				"        AND t_work_log.work_date BETWEEN :fromDate AND :toDate\n" + 
				"        GROUP BY t_work_log.project_id", nativeQuery=true)
		List<GetProjWorkLog> getProjWorkLogAdmOnload(@Param("fromDate") String fromDate,@Param("toDate") String toDate);
	
}
