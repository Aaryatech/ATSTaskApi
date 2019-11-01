package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

 import com.ats.hrmgt.model.GetEmpWorkLog;

public interface GetEmpWorkLogRepo extends JpaRepository<GetEmpWorkLog, Integer>{

	@Query(value = "SELECT\n" + 
			"t_work_log.work_log_id,\n" + 
			"    t_work_log.log_type,\n" + 
			"    t_work_log.project_id,\n" + 
			"    t_work_log.work_type_id,\n" + 
			"    t_work_log.emp_id,\n" + 
			"    t_work_log.work_date,\n" + 
			"     t_work_log.work_desc,\n" + 
			"    project_header.project_title,emp_info.emp_fname,emp_info.emp_sname,\n" + 
			"    COALESCE(\n" + 
			"        (\n" + 
			"            CONCAT(\n" + 
			"                FLOOR(t_work_log.work_hrs / 60),\n" + 
			"                ':',\n" + 
			"                LPAD(MOD(t_work_log.work_hrs, 60), 2, '0')\n" + 
			"            )\n" + 
			"        ),\n" + 
			"        0\n" + 
			"    ) AS work_hrs FROM emp_info,project_header,t_work_log WHERE emp_info.emp_id=t_work_log.emp_id AND t_work_log.project_id=project_header.project_id AND t_work_log.del_status=1 AND t_work_log.is_active=1 AND t_work_log.project_id=:projId  AND t_work_log.emp_id=:empId AND t_work_log.work_date BETWEEN :fromDate AND :toDate ", nativeQuery = true)
	List<GetEmpWorkLog> getGetEmpWorkLog(@Param("empId") int empId,@Param("projId") int projId,@Param("fromDate") String fromDate,@Param("toDate") String toDate);
	

	@Query(value = "SELECT\n" + 
			"t_work_log.work_log_id,\n" + 
			"    t_work_log.log_type,\n" + 
			"    t_work_log.project_id,\n" + 
			"    t_work_log.work_type_id,\n" + 
			"    t_work_log.emp_id,\n" + 
			"    t_work_log.work_date,\n" + 
			"     t_work_log.work_desc,\n" + 
			"    project_header.project_title,emp_info.emp_fname,emp_info.emp_sname,\n" + 
			"    COALESCE(\n" + 
			"        (\n" + 
			"            CONCAT(\n" + 
			"                FLOOR(t_work_log.work_hrs / 60),\n" + 
			"                ':',\n" + 
			"                LPAD(MOD(t_work_log.work_hrs, 60), 2, '0')\n" + 
			"            )\n" + 
			"        ),\n" + 
			"        0\n" + 
			"    ) AS work_hrs FROM emp_info,project_header,t_work_log WHERE emp_info.emp_id=t_work_log.emp_id AND t_work_log.project_id=project_header.project_id AND t_work_log.del_status=1 AND t_work_log.is_active=1 AND t_work_log.emp_id=:empId  ", nativeQuery = true)
	List<GetEmpWorkLog> getGetEmpWorkLog(@Param("empId") int empId);

	//Sachin 14-10-2019 Next 2 Queries
	
	
	@Query(value = "SELECT\n" + 
			"t_work_log.work_log_id,\n" + 
			"    t_work_log.log_type,\n" + 
			"    t_work_log.project_id,\n" + 
			"    t_work_log.work_type_id,\n" + 
			"    t_work_log.emp_id,\n" + 
			"    t_work_log.work_date,\n" + 
			"     t_work_log.work_desc,\n" + 
			"    project_header.project_title,emp_info.emp_fname,emp_info.emp_sname,\n" + 
			"    COALESCE(\n" + 
			"        (\n" + 
			"            CONCAT(\n" + 
			"                FLOOR(t_work_log.work_hrs / 60),\n" + 
			"                ':',\n" + 
			"                LPAD(MOD(t_work_log.work_hrs, 60), 2, '0')\n" + 
			"            )\n" + 
			"        ),\n" + 
			"        0\n" + 
			"    ) AS work_hrs FROM emp_info,project_header,t_work_log WHERE emp_info.emp_id=t_work_log.emp_id AND"
			+ " t_work_log.project_id=project_header.project_id AND t_work_log.del_status=1 "
			+ "AND t_work_log.is_active=1 "
			+ " AND t_work_log.work_date BETWEEN :fromDate AND :toDate ", nativeQuery = true)
	List<GetEmpWorkLog> getWorkLogAdmOnload(@Param("fromDate") String fromDate,@Param("toDate") String toDate);
	
	
	@Query(value = "SELECT\n" + 
			"t_work_log.work_log_id,\n" + 
			"    t_work_log.log_type,\n" + 
			"    t_work_log.project_id,\n" + 
			"    t_work_log.work_type_id,\n" + 
			"    t_work_log.emp_id,\n" + 
			"    t_work_log.work_date,\n" + 
			"     t_work_log.work_desc,\n" + 
			"    project_header.project_title,emp_info.emp_fname,emp_info.emp_sname,\n" + 
			"    COALESCE(\n" + 
			"        (\n" + 
			"            CONCAT(\n" + 
			"                FLOOR(t_work_log.work_hrs / 60),\n" + 
			"                ':',\n" + 
			"                LPAD(MOD(t_work_log.work_hrs, 60), 2, '0')\n" + 
			"            )\n" + 
			"        ),\n" + 
			"        0\n" + 
			"    ) AS work_hrs FROM emp_info,project_header,t_work_log WHERE emp_info.emp_id=t_work_log.emp_id AND"
			+ " t_work_log.project_id=project_header.project_id AND t_work_log.del_status=1 "
			+ "AND t_work_log.is_active=1 "
			+ " AND t_work_log.work_date BETWEEN :fromDate AND :toDate  and t_work_log.emp_id IN (:empIdList)", nativeQuery = true)
	List<GetEmpWorkLog> getWorkLogAdmBySearch(@Param("fromDate") String fromDate,@Param("toDate") String toDate,
			@Param("empIdList") List<String> empIdList);

	@Query(value=" SELECT\n" + 
			"        t_work_log.work_log_id,\n" + 
			"        t_work_log.log_type,\n" + 
			"        t_work_log.project_id,\n" + 
			"        t_work_log.work_type_id,\n" + 
			"        t_work_log.emp_id,\n" + 
			"        t_work_log.work_date,\n" + 
			"        t_work_log.work_desc,\n" + 
			"        project_header.project_title,\n" + 
			"        emp_info.emp_fname,\n" + 
			"        emp_info.emp_sname,\n" + 
			"        COALESCE(         (             CONCAT(                 FLOOR(t_work_log.work_hrs / 60),\n" + 
			"        ':',\n" + 
			"        LPAD(MOD(t_work_log.work_hrs,\n" + 
			"        60),\n" + 
			"        2,\n" + 
			"        '0')             )         ),\n" + 
			"        0     ) AS work_hrs \n" + 
			"    FROM\n" + 
			"        emp_info,\n" + 
			"        project_header,\n" + 
			"        t_work_log \n" + 
			"    WHERE\n" + 
			"        emp_info.emp_id=t_work_log.emp_id \n" + 
			"        AND t_work_log.project_id=project_header.project_id \n" + 
			"        AND t_work_log.del_status=1 \n" + 
			"        AND t_work_log.is_active=1  \n" + 
			"        AND t_work_log.work_date BETWEEN :fromDate AND :toDate  \n" + 
			"        and t_work_log.project_id=:projId",nativeQuery=true)
	List<GetEmpWorkLog> getEmpWorkLogByProjId(@Param("projId") int projId, @Param("fromDate") String fromDate,@Param("toDate") String toDate);

}
