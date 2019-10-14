package com.ats.hrmgt.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class GetEmpLogGrpByDate {

	@Id
	private String id;
	
	private int empId;
	

	private Date workDate;

	private String projectTitle;

	private String empFname;

	private String empSname;

	private String workHrs;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	@JsonFormat(locale = "Locale.ENGLISH", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")// 
	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getEmpFname() {
		return empFname;
	}

	public void setEmpFname(String empFname) {
		this.empFname = empFname;
	}

	public String getEmpSname() {
		return empSname;
	}

	public void setEmpSname(String empSname) {
		this.empSname = empSname;
	}

	public String getWorkHrs() {
		return workHrs;
	}

	public void setWorkHrs(String workHrs) {
		this.workHrs = workHrs;
	}

	@Override
	public String toString() {
		return "GetEmpLogGrpByDate [id=" + id + ", empId=" + empId + ", workDate=" + workDate + ", projectTitle="
				+ projectTitle + ", empFname=" + empFname + ", empSname=" + empSname + ", workHrs=" + workHrs + "]";
	}
	
}
