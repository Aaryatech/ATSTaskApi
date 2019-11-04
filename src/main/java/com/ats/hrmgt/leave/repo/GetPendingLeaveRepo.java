package com.ats.hrmgt.leave.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ats.hrmgt.leave.model.GetPendingLeaveCount;
@Repository
public interface GetPendingLeaveRepo extends JpaRepository<GetPendingLeaveCount, Integer> {

	@Query(value="SELECT 0 as id, COALESCE((SELECT COUNT(leave_id) FROM leave_apply WHERE ex_int1=1 AND del_status=1),0) as pending_leave_count",nativeQuery=true)
	GetPendingLeaveCount getPendingLeaveCount();
	
}
