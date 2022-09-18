package com.situ.model;

import java.time.LocalDate;

public class Staff {
	
	private Integer id;
	private String staffId;
	private String staffName;
	private String staffJob;
	private String staffSex;
	private String staffAge;
	private String staffEmail;
	private LocalDate staffEntrytime;
	private String staffWorkingyears;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffJob() {
		return staffJob;
	}
	public void setStaffJob(String staffJob) {
		this.staffJob = staffJob;
	}
	public String getStaffSex() {
		return staffSex;
	}
	public void setStaffSex(String staffSex) {
		this.staffSex = staffSex;
	}
	public String getStaffAge() {
		return staffAge;
	}
	public void setStaffAge(String staffAge) {
		this.staffAge = staffAge;
	}
	public String getStaffEmail() {
		return staffEmail;
	}
	public void setStaffEmail(String staffEmail) {
		this.staffEmail = staffEmail;
	}
	public LocalDate getStaffEntrytime() {
		return staffEntrytime;
	}
	public void setStaffEntrytime(LocalDate staffEntrytime) {
		this.staffEntrytime = staffEntrytime;
	}
	public String getStaffWorkingyears() {
		return staffWorkingyears;
	}
	public void setStaffWorkingyears(String staffWorkingyears) {
		this.staffWorkingyears = staffWorkingyears;
	}
	
	
}
