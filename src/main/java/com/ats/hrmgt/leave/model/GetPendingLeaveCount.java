package com.ats.hrmgt.leave.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GetPendingLeaveCount {

	@Id
	private int id;
	private int pendingLeaveCount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPendingLeaveCount() {
		return pendingLeaveCount;
	}
	public void setPendingLeaveCount(int pendingLeaveCount) {
		this.pendingLeaveCount = pendingLeaveCount;
	}
	@Override
	public String toString() {
		return "GetPendingLeaveCount [id=" + id + ", pendingLeaveCount=" + pendingLeaveCount + ", getId()=" + getId()
				+ ", getPendingLeaveCount()=" + getPendingLeaveCount() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
