package com.situ.service.impl;

import java.util.List;

import com.situ.dao.StaffDao;
import com.situ.dao.impl.StaffDaoImpl;
import com.situ.model.Staff;
import com.situ.model.StaffBean;
import com.situ.service.StaffService;
import com.situ.utils.Pagin;

public class StaffServiceImpl implements StaffService{
	
	private final StaffDao dao = new StaffDaoImpl();
	
	
	@Override
	public List<Staff> findAll(Pagin pi,StaffBean staffbean) {
		
		return dao.findAll(pi,staffbean);
	}


	@Override
	public int deleteByIds(List<Integer> ids) {
		return dao.deleteByIds(ids);
	}


	@Override
	public boolean save(Staff staff) {
		
		return dao.save(staff);
	}


	@Override
	public Staff findById(Integer id) {
		return dao.findById(id);
	}


	@Override
	public boolean update(Staff staff) {
		return dao.update(staff);
	}
	
	
	
}
