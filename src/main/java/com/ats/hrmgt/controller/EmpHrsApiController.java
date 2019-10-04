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

import com.ats.hrmgt.model.GetEmpWorkLog;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.WorkLog;
import com.ats.hrmgt.model.WorkType;
import com.ats.hrmgt.repository.GetEmpWorkLogRepo;
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

}
