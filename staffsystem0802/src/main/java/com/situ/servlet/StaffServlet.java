package com.situ.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.situ.model.Staff;
import com.situ.model.StaffBean;
import com.situ.service.StaffService;
import com.situ.service.impl.StaffServiceImpl;
import com.situ.utils.Pagin;
import com.situ.utils.StringUtils;

@WebServlet("/staff/*")
public class StaffServlet extends HttpServlet {
	private static final long serialVersionUID = 5759529912269774311L;
	private final StaffService ss = new StaffServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pi = req.getPathInfo();
		if ("/list".equals(pi)) {
			doPost(req, resp);
		} else if ("/add".equals(pi)) {
			// 添加
			String error = (String) req.getSession().getAttribute("#staff-add-error");
			if (error != null) {
				req.setAttribute("error", error);
				req.getSession().removeAttribute("#staff-add-error");
			}

			// 是否添加成功
			Boolean success = (Boolean) req.getSession().getAttribute("#staff-add-success");
			if (success != null) {
				req.setAttribute("success", true);
				req.getSession().removeAttribute("#staff-add-success");
			}

			req.getRequestDispatcher("/WEB-INF/jsp/staff/add.jsp").forward(req, resp);
		} else if ("/edit".equals(pi)) {// 跳转到修改页
			String id = req.getParameter("id");
			if (id == null) {
				req.setAttribute("error", "要修改的员工编号不可为空");
			} else {
				Staff staff = ss.findById(Integer.valueOf(id));
				if (staff == null) {
					req.setAttribute("error", "您要修改的员工不存在");
				} else {
					req.setAttribute("staff", staff);
				}
			}

			String error = (String) req.getSession().getAttribute("#staff-edit-error");
			if (error != null) {
				req.setAttribute("error", error);
				req.getSession().removeAttribute("#staff-edit-error");
			}
			// 是否修改成功
			Boolean success = (Boolean) req.getSession().getAttribute("#staff-edit-success");
			if (success != null) {
				req.setAttribute("success", true);
				req.getSession().removeAttribute("#staff-edit-success");
			}

			req.getRequestDispatcher("/WEB-INF/jsp/staff/edit.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pi = req.getPathInfo();
		if ("/list".equals(pi)) {
			list(req, resp);
		} else if ("/delete".equals(pi)) {
			// 删除
			delete(req, resp);
		} else if ("/add".equals(pi)) {
			// 提交添加
			add(req, resp);
		} else if ("/edit".equals(pi)) {
			// 提交修改
			edit(req, resp);
		}

	}

	// 查询
	private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer pageno = 1;
		//页面记录数
		Integer pageseize = 14;
		// 获取参数，改变当前页码和数量
		String strpage = req.getParameter("pageNo");
		String strpagesize = req.getParameter("pageSize");
		if (strpage != null) {
			try {
				pageno = Integer.valueOf(strpage);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		if (strpagesize != null) {
			try {
				pageseize = Integer.valueOf(strpagesize);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		String staffid = req.getParameter("staffid");
		String name = req.getParameter("name");
		String sex = req.getParameter("sex");
		String entrytime = req.getParameter("entrytime");
		String email = req.getParameter("email");
		String workingtime = req.getParameter("workingtime");

		StaffBean staffbean = new StaffBean();

		if (StringUtils.isNotBlank(staffid)) {
			staffbean.setStaffId(staffid);
		}
		if (StringUtils.isNotBlank(name)) {
			staffbean.setStaffName(name);
		}
		if (StringUtils.isNotBlank(sex)) {
			staffbean.setStaffSex(sex);
		}
		if (StringUtils.isNotBlank(entrytime)) {
			staffbean.setday(entrytime);
		}
		if (StringUtils.isNotBlank(email)) {
			staffbean.setStaffEmail(email);
		}
		if (StringUtils.isNotBlank(workingtime)) {
			staffbean.setStaffWorkingyears(workingtime);
		}
		// 获得页码信息
		Pagin pi = new Pagin(pageno, pageseize);
		StaffService ss = new StaffServiceImpl();
		List<Staff> staffs = ss.findAll(pi, staffbean);
		req.setAttribute("pi", pi);
		req.setAttribute("staffs", staffs);
		req.setAttribute("staffbean", staffbean);
		req.getRequestDispatcher("/WEB-INF/jsp/staff/list.jsp").forward(req, resp);
	}

	// 删除
	private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		resp.setContentType("application/json;charset=utf-8");

		String[] strIds = req.getParameterValues("ids");

		List<Integer> ids = Stream.of(strIds).map(Integer::valueOf).toList();
		int rows = ss.deleteByIds(ids);
		PrintWriter pw = resp.getWriter();

		String result = "{\"success\":true,\"rows\":" + rows + "}";

		pw.write(result);

		pw.flush();
		pw.close();

	}

	// 添加
	private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String staffId = req.getParameter("staffId");
		String name = req.getParameter("name");
		String sex = req.getParameter("sex");
		String job = req.getParameter("job");
		String age = req.getParameter("age");
		String email = req.getParameter("email");
		String entrytime = req.getParameter("entrytime");
		String workingyears = req.getParameter("workingyears");

		Staff staff = new Staff();
		// 参数校验
		if (StringUtils.isBlank(staffId)) {
			req.getSession().setAttribute("#staff-add-error", "员工工号不可为空");
			resp.sendRedirect(req.getContextPath() + "/staff/add");
			return;
		}

		staff.setStaffId(staffId);

		if (StringUtils.isNotBlank(name)) {
			staff.setStaffName(name);
		}

		staff.setStaffSex(sex);

		if (StringUtils.isNotBlank(job)) {
			staff.setStaffJob(job);
		}

		if (StringUtils.isNotBlank(age)) {
			staff.setStaffAge(age);
		}

		if (StringUtils.isNotBlank(email)) {
			staff.setStaffEmail(email);
		}
		if (StringUtils.isNotBlank(entrytime)) {
			staff.setStaffEntrytime(LocalDate.parse(entrytime, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		}

		if (StringUtils.isNotBlank(workingyears)) {
			staff.setStaffWorkingyears(workingyears);
		}

		try {
			boolean success = ss.save(staff);
			if (success) {
				req.getSession().setAttribute("#staff-add-success", true);
				resp.sendRedirect(req.getContextPath() + "/staff/add");
			} else {
				req.getSession().setAttribute("#staff-add-error", "信息添加失败");
				resp.sendRedirect(req.getContextPath() + "/staff/add");
			}
		} catch (Exception e) {
			req.getSession().setAttribute("#staff-add-error", "信息添加失败，请重新确认");
			resp.sendRedirect(req.getContextPath() + "/staff/add");
		}
	}

	// 提交修改
	private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//选择的id
		String id = req.getParameter("id");
		// 数据库中的员工
		Staff staff = ss.findById(Integer.valueOf(id));

		String staffId = req.getParameter("staffId");
		String name = req.getParameter("name");
		String sex = req.getParameter("sex");
		String job = req.getParameter("job");
		String age = req.getParameter("age");
		String email = req.getParameter("email");
		String entrytime = req.getParameter("entrytime");
		String workingyears = req.getParameter("workingyears");

		// 参数校验
		if (StringUtils.isBlank(staffId)) {
			req.getSession().setAttribute("#staff-add-error", "员工工号不可为空");
			resp.sendRedirect(req.getContextPath() + "/staff/add");
			return;
		}

		staff.setStaffId(staffId);

		if (StringUtils.isNotBlank(name)) {
			staff.setStaffName(name);
		}

		staff.setStaffSex(sex);

		if (StringUtils.isNotBlank(job)) {
			staff.setStaffJob(job);
		}

		staff.setStaffAge(age);

		staff.setStaffEmail(email);

		if (StringUtils.isNotBlank(entrytime)) {
			staff.setStaffEntrytime(LocalDate.parse(entrytime, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		}

		staff.setStaffWorkingyears(workingyears);

		try {
			boolean success = ss.update(staff);
			if (success) {
				req.getSession().setAttribute("#staff-edit-success", true);
				resp.sendRedirect(req.getContextPath() + "/staff/edit?id=" + staff.getId());
			} else {
				req.getSession().setAttribute("#staff-edit-error", "修改失败");
				resp.sendRedirect(req.getContextPath() + "/staff/edit?id=" + staff.getId());
			}
		} catch (Exception e) {
			req.getSession().setAttribute("#staff-add-error", "失败,请重新确认信息");
			resp.sendRedirect(req.getContextPath() + "/staff/edit?id=" + staff.getId());
		}
	}

}
