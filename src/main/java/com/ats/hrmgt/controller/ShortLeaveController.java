package com.ats.hrmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.GetEmpShortLeaves;
import com.ats.hrmgt.model.ShortLeave;
import com.ats.hrmgt.repository.GetEmpShortLeavesRepo;
import com.ats.hrmgt.repository.ShortLeaveRepo;

@RestController
public class ShortLeaveController {

	@Autowired
	ShortLeaveRepo shortLeaveRepo;
	
	@Autowired GetEmpShortLeavesRepo getEmpShortLeavesRepo;

	@RequestMapping(value = { "/saveShortLeave" }, method = RequestMethod.POST)
	public @ResponseBody ShortLeave saveShortLeave(@RequestBody ShortLeave shortLeave) {

		ShortLeave saveRes = new ShortLeave();
		try {

			saveRes = shortLeaveRepo.saveAndFlush(shortLeave);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return saveRes;

	}
	
	
	@RequestMapping(value = { "/getShortLeaveList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetEmpShortLeaves> getShortLeaveList(@RequestParam String fromDate, 
			@RequestParam String toDate,
			@RequestParam List<String> empIdList) {

		List<GetEmpShortLeaves> shLeaveList = new ArrayList<GetEmpShortLeaves>();
		try {

			if(empIdList.get(0).equalsIgnoreCase("ALL")){
			shLeaveList = getEmpShortLeavesRepo.getEmpShortLeaves(fromDate, toDate);
			}else {
				shLeaveList = getEmpShortLeavesRepo.getEmpShortLeaves(fromDate, toDate, empIdList);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return shLeaveList;

	}
	

}
