package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.GetEmpShortLeaves;

public interface GetEmpShortLeavesRepo extends JpaRepository<GetEmpShortLeaves, Integer> {

	@Query(value = "  select UUID() as id,emp_info.emp_id, emp_info.emp_fname,  emp_info.emp_sname,      \n"
			+ "      coalesce((select count(t_short_leave.sh_leave_id) from t_short_leave where "
			+ "t_short_leave.sh_leave_date between :fromDate and :toDate and "
			+ "emp_info.emp_id=t_short_leave.sh_leave_emp_id and t_short_leave.del_status=1),'-') as sh_leave_count\n"
			+ "        from emp_info where emp_info.del_status=1    group by emp_info.emp_id  ", nativeQuery = true)

	List<GetEmpShortLeaves> getEmpShortLeaves(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

	@Query(value = "  select UUID() as id,emp_info.emp_id, emp_info.emp_fname , emp_info.emp_sname,      \n"
			+ "      coalesce((select count(t_short_leave.sh_leave_id) from t_short_leave where "
			+ "t_short_leave.sh_leave_date between :fromDate and :toDate and "
			+ "emp_info.emp_id=t_short_leave.sh_leave_emp_id and t_short_leave.del_status=1),'-') as sh_leave_count\n"
			+ "        from emp_info where emp_info.emp_id IN (:empIdList) and emp_info.del_status=1  group by emp_info.emp_id  ", nativeQuery = true)
	List<GetEmpShortLeaves> getEmpShortLeaves(@Param("fromDate") String fromDate, @Param("toDate") String toDate,
			@Param("empIdList") List<String> empIdList);

	
	@Query(value="select\n" + 
			"        UUID() as id,\n" + 
			"        emp_info.emp_id,\n" + 
			"        emp_info.emp_fname,\n" + 
			"        emp_info.emp_sname,\n" + 
			"        coalesce((select\n" + 
			"            count(t_short_leave.sh_leave_id) \n" + 
			"        from\n" + 
			"            t_short_leave \n" + 
			"        where\n" + 
			"            t_short_leave.sh_leave_date between :fromDate and :toDate\n" + 
			"            \n" + 
			"            and emp_info.emp_id=t_short_leave.sh_leave_emp_id \n" + 
			"            and t_short_leave.del_status=1),\n" + 
			"        '-') as sh_leave_count         \n" + 
			"    from\n" + 
			"        emp_info \n" + 
			"    where\n" + 
			"        emp_info.del_status=1    \n" + 
			"        and emp_info.emp_id=:empId",nativeQuery=true)
	GetEmpShortLeaves getEmpShortLeavesInfo(@Param("fromDate") String fromDate, @Param("toDate") String toDate,
			@Param("empId") int empId);

}
