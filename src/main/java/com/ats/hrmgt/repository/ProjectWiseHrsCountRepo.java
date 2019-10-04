package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
 
 import com.ats.hrmgt.model.ProjectWiseHrsCount;

public interface ProjectWiseHrsCountRepo extends JpaRepository<ProjectWiseHrsCount, Integer>{
	

	@Query(value = " SELECT  project_header.project_id,project_header.project_title, COALESCE(\n" + 
			"        (\n" + 
			"            CONCAT(\n" + 
			"                FLOOR(SUM(t_work_log.work_hrs )/ 60),\n" + 
			"                ':',\n" + 
			"                LPAD(MOD(SUM(t_work_log.work_hrs), 60), 2, '0')\n" + 
			"            )\n" + 
			"        ),\n" + 
			"        0\n" + 
			"    ) AS work_hrs  FROM project_header,t_work_log WHERE t_work_log.project_id=project_header.project_id AND project_header.del_status=1\n" + 
			"    GROUP BY project_header.project_id", nativeQuery = true)

	List<ProjectWiseHrsCount> getProjectWiseHrsCount();


}
