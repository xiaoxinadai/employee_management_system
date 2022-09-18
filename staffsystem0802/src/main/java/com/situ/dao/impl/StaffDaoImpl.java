package com.situ.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.situ.common.Global;
import com.situ.dao.StaffDao;
import com.situ.model.Staff;
import com.situ.model.StaffBean;
import com.situ.utils.JdbcUtils;
import com.situ.utils.Pagin;
import com.situ.utils.StringUtils;
import com.situ.utils.JdbcUtils.BeanListHandler;
import com.situ.utils.JdbcUtils.LongResultSetHandler;
import com.situ.utils.JdbcUtils.ResultSetHandler;

public class StaffDaoImpl implements StaffDao{
	
	private final ResultSetHandler<List<Staff>> rsh = new BeanListHandler<Staff>(Staff.class);
	@Override
	public List<Staff> findAll(Pagin pi,StaffBean staffbean) {
		Connection	conn=Global.getConnection();
		try {
			String sql= "select id,staff_id,staff_name,staff_job,staff_sex,staff_age,staff_email,staff_entrytime,staff_workingyears from t_staff ";
			
			//条件查询
			List<Object> ages=new ArrayList<>();
			
			StringBuilder where =new StringBuilder(" where 1=1 ");
			if(StringUtils.isNotBlank(staffbean.getStaffId())) {
				where.append(" and staff_id=? ");
				ages.add(staffbean.getStaffId());
			}
			if(StringUtils.isNotBlank(staffbean.getStaffName())) {
				where.append(" and staff_name like ? ");
				ages.add("%"+staffbean.getStaffName()+"%");
			}
			if(StringUtils.isNotBlank(staffbean.getStaffJob())) {
				where.append(" and staff_job =? ");
				ages.add(staffbean.getStaffJob());
			}
			if(StringUtils.isNotBlank(staffbean.getStaffSex())) {
				where.append(" and staff_sex=? ");
				ages.add(staffbean.getStaffSex());
			}
			if(StringUtils.isNotBlank(staffbean.getStaffAge())) {
				where.append(" and staff_age=? ");
				ages.add(staffbean.getStaffAge());
			}
			if(StringUtils.isNotBlank(staffbean.getStaffEmail())) {
				where.append(" and staff_email =? ");
				ages.add(staffbean.getStaffEmail());
			}
			if (staffbean.getDayRangForm() != null) {
                where.append(" and staff_entrytime > ?");
                ages.add(staffbean.getDayRangForm());
            }
			if(staffbean.getDayRangTo()!=null) {
				where.append(" and staff_entrytime<=? ");
				ages.add(staffbean.getDayRangTo());
			}
			if(StringUtils.isNotBlank(staffbean.getStaffWorkingyears())) {
				where.append(" and staff_workingyears = ? ");
				ages.add(staffbean.getStaffWorkingyears());
			}
			
			//查询总数
			String  contsql="select count(0) from t_staff "+where.toString();
			Long tot=JdbcUtils.query(conn, new LongResultSetHandler(), contsql,ages.toArray());
			pi.setTotal(tot);
			
			sql+=where.toString()+" limit ?,? ";
			ages.add(pi.getOffset());
			ages.add(pi.getLimit());
			
			List<Staff> user=JdbcUtils.query(conn, rsh, sql, ages.toArray());
			return  user;
		}finally {
			JdbcUtils.closeConnection(conn);
		}
	}
	@Override
	public int deleteByIds(List<Integer> ids) {
		
		Objects.requireNonNull(ids);
		if(ids.size()<1) {
			throw new RuntimeException("要删除的员工编号不可为空");
			
		}
		
		
		Connection conn = Global.getConnection();
		StringBuilder sb = new StringBuilder( "delete from t_staff where id in (");
		List<Object> args = new ArrayList<>();
		
		
		for(Integer id:ids) {
			sb.append("?,");
			args.add(id);
		}
		
		//去除多余符号
		String sql = sb.toString();
		sql = sql.substring(0, sql.length() - 1);
		sql+=")";
		
		try {
			return JdbcUtils.update(conn, sql, args.toArray());
		} finally {
			JdbcUtils.closeConnection(conn);
		}
	}
	
	//保存数据
	@Override
	public boolean save(Staff staff) {
		Connection conn = Global.getConnection();
        String sql =
            "insert into t_staff (staff_id,staff_name,staff_job,staff_sex,staff_age,staff_email,staff_entrytime,staff_workingyears) values (?,?,?,?,?,?,?,?)";

        try {
            int rows = JdbcUtils.update(conn, sql, staff.getStaffId(), staff.getStaffName(), staff.getStaffJob(), staff.getStaffSex(),
            		staff.getStaffAge(), staff.getStaffEmail(), staff.getStaffEntrytime(),staff.getStaffWorkingyears());
            return rows > 0;
        } finally {
            JdbcUtils.closeConnection(conn);
        }
		
		
	}
	//查找对应id的员工的信息
	@Override
	public Staff findById(Integer id) {
		Connection conn = Global.getConnection();
		
		try {
			String sql= "select id,staff_id,staff_name,staff_job,staff_sex,staff_age,staff_email,staff_entrytime,staff_entrytime,staff_workingyears from t_staff where id = ?";
		    
			List<Staff> staffs = JdbcUtils.query(conn, rsh, sql, id);
            return staffs.size() == 0 ? null : staffs.get(0);
        } finally {
            JdbcUtils.closeConnection(conn);
        }
		
	}
	
	//修改
	@Override
	public boolean update(Staff staff) {
		Connection conn = Global.getConnection();
		
		try {
			String sql = "update t_staff set staff_id = ?,staff_name = ?,staff_job = ?,staff_sex = ?,staff_age = ?,staff_email = ?,staff_entrytime = ?,staff_workingyears = ? where id = ?";
		    
			int rows = JdbcUtils.update(conn,sql,staff.getStaffId(),staff.getStaffName(),staff.getStaffJob(),staff.getStaffSex(),staff.getStaffAge(),staff.getStaffEmail(),staff.getStaffEntrytime(),staff.getStaffWorkingyears(),staff.getId());
            return rows> 0;
        } finally {
            JdbcUtils.closeConnection(conn);
        }
	}
	
	
	
}
