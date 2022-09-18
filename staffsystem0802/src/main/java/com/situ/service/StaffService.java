package com.situ.service;

import java.util.List;

import com.situ.model.Staff;
import com.situ.model.StaffBean;
import com.situ.utils.Pagin;

/**
 * 员工业务类接口
 * @author adai
 *
 */
public interface StaffService {
	/*
	 * 查询所有学生
	 */
	
	List<Staff> findAll(Pagin pi,StaffBean staffbean);
	
	//根据主键编号查询唯一学生
	Staff findById(Integer id);
	
	//删除
	int deleteByIds(List<Integer> ids);
	
	//保存
	boolean save(Staff staff);
	
	//修改员工
	boolean update(Staff staff);
	
}
