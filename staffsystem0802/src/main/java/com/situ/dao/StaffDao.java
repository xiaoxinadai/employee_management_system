package com.situ.dao;

import java.util.List;

import com.situ.model.Staff;
import com.situ.model.StaffBean;
import com.situ.utils.Pagin;

public interface StaffDao {
	
	public List<Staff> findAll(Pagin pi,StaffBean staffbean);
	
	int deleteByIds(List<Integer> ids);
	
	boolean save(Staff staff);
	
	Staff findById(Integer id);
	
	boolean update(Staff staff);
	
}
