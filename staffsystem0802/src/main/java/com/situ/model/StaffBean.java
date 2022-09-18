package com.situ.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.situ.utils.StringUtils;


public class StaffBean extends Staff{
	
	private  LocalDate dayRangForm;
	private  LocalDate dayRangTo;
	private   String dayrange;
	public LocalDate getDayRangForm() {
		return dayRangForm;
	}
	public void setDayRangForm(LocalDate dayRangForm) {
		this.dayRangForm = dayRangForm;
	}
	public LocalDate getDayRangTo() {
		return dayRangTo;
	}
	public void setDayRangTo(LocalDate dayRangTo) {
		this.dayRangTo = dayRangTo;
	}
	
	
	public  void setday(String dayrange) {
		this.dayrange=dayrange;
		if (StringUtils.isNotBlank(dayrange) ) {
			String[]  t=dayrange.split(" - ");
			if(StringUtils.isNotBlank(t[0])){
				this.dayRangForm=LocalDate.parse(t[0],DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			}
			if(StringUtils.isNotBlank(t[1])){
				this. dayRangTo=LocalDate.parse(t[1],DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			}
		}
		
	}
	public String getDayrange() {
		return this.dayrange;
	}
	
}
