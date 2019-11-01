package com.ats.hrmgt.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.leave.repo.EmpLeaveHistoryRepRepo;
import com.ats.hrmgt.model.EmpLeaveHistoryRep;
import com.ats.hrmgt.model.GetEmpLogGrpByDate;
import com.ats.hrmgt.model.GetEmpWorkLog;
import com.ats.hrmgt.model.GetProjWorkLog;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.ProjTypeWorkLog;
import com.ats.hrmgt.model.ProjectWiseHrsCount;
import com.ats.hrmgt.model.WorkLog;
import com.ats.hrmgt.model.WorkType;
import com.ats.hrmgt.repository.GetEmpLogGrpByDateRepo;
import com.ats.hrmgt.repository.GetEmpWorkLogRepo;
import com.ats.hrmgt.repository.GetProjWorkLogRepo;
import com.ats.hrmgt.repository.ProjTypeWorkLogRepo;
import com.ats.hrmgt.repository.ProjectWiseHrsCountRepo;
import com.ats.hrmgt.repository.WorkLogRepo;
import com.ats.hrmgt.repository.WorkTypeRepo;

@RestController
public class EmpHrsApiController {

	// **********************Add work hrs**************************************

	@Autowired
	WorkLogRepo workLogRepo;

	@RequestMapping(value = { "/saveWorkLog" }, method = RequestMethod.POST)
	public @ResponseBody WorkLog saveWorkLog(@RequestBody WorkLog workType) {

		WorkLog save = new WorkLog();
		try {

			save = workLogRepo.saveAndFlush(workType);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}
	
	@Autowired 
	GetEmpWorkLogRepo getEmpWorkLogRepo;

	@RequestMapping(value = { "/getEmpWorkLogByProjId" }, method = RequestMethod.POST)
	public @ResponseBody List<GetEmpWorkLog> getWorkTypeList(@RequestParam("empId") int empId,
			@RequestParam("projId") int projId, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {

		List<GetEmpWorkLog> list = new ArrayList<GetEmpWorkLog>();
		
	//	System.out.println("dates are"+fromDate+toDate+projId+empId);
		try {
			if(projId!=0 && fromDate!="" && toDate!="") {
				list = getEmpWorkLogRepo.getGetEmpWorkLog(empId,projId,fromDate,toDate);
			}else {
				list = getEmpWorkLogRepo.getGetEmpWorkLog(empId);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	
	@RequestMapping(value = { "/getWorkLogAdm" }, method = RequestMethod.POST)
	public @ResponseBody List<GetEmpWorkLog> getWorkLogAdmOnload(@RequestParam("empIdList") List<String> empIdList,
			  @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {

		List<GetEmpWorkLog> list = new ArrayList<GetEmpWorkLog>();
		System.err.println("empIdList " +empIdList.toString());
	//	System.out.println("dates are"+fromDate+toDate+projId+empId);
		try {
			if(empIdList.contains("ALL")) {
				System.err.println("A");
				list = getEmpWorkLogRepo.getWorkLogAdmOnload(fromDate, toDate);
			}else {
				System.err.println("B");
				list = getEmpWorkLogRepo.getWorkLogAdmBySearch(fromDate, toDate, empIdList);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	/****************************************************************************/
	
	
	@RequestMapping(value = { "/getEmpWorkLogAdm" }, method = RequestMethod.POST)
	public @ResponseBody List<GetEmpWorkLog> getEmpWorkLogAdm(@RequestParam("projId") int projId,
			  @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {

		List<GetEmpWorkLog> list = new ArrayList<GetEmpWorkLog>();
		
		System.out.println("dates are"+fromDate+toDate+projId+projId);
		try {
			
				list = getEmpWorkLogRepo.getEmpWorkLogByProjId(projId, fromDate, toDate);
		
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@Autowired  GetProjWorkLogRepo getProjRepo;
	//Mahendra 
	//01-11-2019
	@RequestMapping(value = { "/getProjWorkLog" }, method = RequestMethod.POST)
	public @ResponseBody List<GetProjWorkLog> getProjWorkLog(@RequestParam("projIdList") List<String> projIdList,
			  @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {

		List<GetProjWorkLog> list = new ArrayList<GetProjWorkLog>();
		System.err.println("projIdList " +projIdList.toString());
	//	System.out.println("dates are"+fromDate+toDate+projId+empId);
		try {
			if(projIdList.contains("ALL")) {
				System.err.println("A");
				list = getProjRepo.getProjWorkLogAdmOnload(fromDate, toDate);
			}else {
				System.err.println("B");
				list = getProjRepo.getProjWorkLogAdmBySearch(fromDate, toDate, projIdList);
			}
			System.out.println("LogList--------------"+list);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@Autowired ProjTypeWorkLogRepo projTypeRepo;
	@RequestMapping(value = { "/getProjTypeWorkLogAdm" }, method = RequestMethod.POST)
	public @ResponseBody List<ProjTypeWorkLog> getProjTypeWorkLogAdm(@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {

		List<ProjTypeWorkLog> list = new ArrayList<ProjTypeWorkLog>();
		try {
				list = projTypeRepo.getProjTypeWorkLogAdmBySearch(fromDate, toDate);
			
				System.out.println("LogList--------------"+list);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	/************************************************************************************/
	
	
	@Autowired GetEmpLogGrpByDateRepo getEmpLogGrpByDateRepo;
	
	@RequestMapping(value = { "/getWorkLogGrpByDate" }, method = RequestMethod.POST)
	public @ResponseBody List<GetEmpLogGrpByDate> getWorkLogGrpByDate(@RequestParam("empIdList") List<String> empIdList,
			  @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {

		List<GetEmpLogGrpByDate> list = new ArrayList<GetEmpLogGrpByDate>();
		System.err.println("empIdList " +empIdList.toString());
	//	System.out.println("dates are"+fromDate+toDate+projId+empId);
		try {
			if(empIdList.contains("ALL")) {
				System.err.println("A");
				list = getEmpLogGrpByDateRepo.getWorkLogAdmOnload(fromDate, toDate);
			}else {
				System.err.println("B");
				list = getEmpLogGrpByDateRepo.getWorkLogAdmBySearch(fromDate, toDate, empIdList);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	
	
	
	@RequestMapping(value = { "/getWorkLogById" }, method = RequestMethod.POST)
	public @ResponseBody WorkLog getWorkLogById(@RequestParam("workLogId") int workLogId) {

		WorkLog company = new WorkLog();
		try {

			company = workLogRepo.findByWorkLogIdAndDelStatus(workLogId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return company;

	}
	
	// **********************Add WorkType**************************************


	@RequestMapping(value = { "/getWorkTypeList" }, method = RequestMethod.GET)
	public @ResponseBody List<WorkType> getWorkTypeList() {

		List<WorkType> list = new ArrayList<WorkType>();
		try {

			list = workTypeRepo.findByDelStatusOrderByWorkTypeIdDesc(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@Autowired
	WorkTypeRepo workTypeRepo;

	@RequestMapping(value = { "/saveWorkType" }, method = RequestMethod.POST)
	public @ResponseBody WorkType saveWorkType(@RequestBody WorkType workType) {

		WorkType save = new WorkType();
		try {

			save = workTypeRepo.saveAndFlush(workType);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	

	@RequestMapping(value = { "/getWorkTypeById" }, method = RequestMethod.POST)
	public @ResponseBody WorkType getCompanyById(@RequestParam("workTypeId") int workTypeId) {

		WorkType company = new WorkType();
		try {

			company = workTypeRepo.findByWorkTypeIdAndDelStatus(workTypeId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return company;

	}

	@RequestMapping(value = { "/deleteWorkType" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteWorkType(@RequestParam("workTypeId") int workTypeId) {

		Info info = new Info();

		try {

			int delete = workTypeRepo.deleteWorkType(workTypeId);

			if (delete > 0) {
				info.setError(false);
				info.setMsg("deleted");
			} else {
				info.setError(true);
				info.setMsg("failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("failed");
		}

		return info;

	}
	
	//dashboard
	
	@Autowired
	EmpLeaveHistoryRepRepo empLeaveHistoryRepRepo;
	
	
	@RequestMapping(value = { "/getLeaveCountOfAllEmployee" }, method = RequestMethod.GET)
	public @ResponseBody List<EmpLeaveHistoryRep> getLeaveCountOfAllEmployee(
		) {
		List<EmpLeaveHistoryRep> list = new ArrayList<EmpLeaveHistoryRep>();

		try {

			list = empLeaveHistoryRepRepo.getEmpLeaveHistoryRepAll();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@Autowired
	ProjectWiseHrsCountRepo projectWiseHrsCountRepo;
	
	@RequestMapping(value = { "/getProjectHrsCount" }, method = RequestMethod.GET)
	public @ResponseBody List<ProjectWiseHrsCount> getProjectHrsCount(
		) {
		List<ProjectWiseHrsCount> list = new ArrayList<ProjectWiseHrsCount>();

		try {

			list = projectWiseHrsCountRepo.getProjectWiseHrsCount();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	
	
	

}
