package com.situ.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.situ.common.Global;
import com.situ.model.User;
import com.situ.service.UserService;
import com.situ.service.impl.UserServiceImpl;

//这样写*可以代表login、logout等
@WebServlet("/user/*")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 7772673371688057819L;

	/*
	 * 多态的使用
	 */
	private final UserService us = new UserServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 通过PathInfo获取*
		String pi = req.getPathInfo();

		// 要跳转到登录页，则放行
		if ("/login".equals(pi) && req.getMethod().toLowerCase().equals("get")) {
			// 跳转到登录页面

			/*
			 * 取出错误信息 如果错误信息不为空，则将错误信息添加到请求中，，并将session中的错误移除
			 */
			String error = (String) req.getSession().getAttribute("#login_error");
			if (error != null) { // 说明有错误

				// 添加
				req.setAttribute("error", error);
				// 移除
				req.getSession().removeAttribute("#login_error");

			}
			
			/*
			 * 取出用户名
			 */
			String username = (String) req.getSession().getAttribute("#longin_username");
			if(username!=null) {
				/*
				 * 重新存入用户名
				 */
				req.setAttribute("username", username);
				req.removeAttribute("#longin_username");
			}
			
			

			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);

		} else if ("/login".equals(pi) && req.getMethod().toLowerCase().equals("post")) {// 登录认证
			login(req, resp);
		} else if ("/logout".equals(pi)) {// 注销
			/*
			 * 如何注销 将session中的用户移除 并将页面跳转离开 到达首页
			 */
			req.getSession().removeAttribute(Global.LOGIN_USER_KEY);
			resp.sendRedirect(req.getContextPath() + "/index");

		}else if("/exists".equals(pi)) {
			exists(req, resp);
		}
	}

	/*
	 * 如何实现认证
	 * 
	 */
	// 认证方法
	private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		/*
		 * 获取用户输入的用户名和密码
		 */
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		//用户名和密码需要校验
		
		

		User dbUser = us.findByUsername(username);
		if (dbUser == null) {
			req.getSession().setAttribute("#login_error", "用户名不存在");
			
			/*
			 * 当密码输入错误或者其他原因没登陆成功时，用户往往希望能够继续保留刚才输入的用户名
			 */
			req.getSession().setAttribute("#longin_username", username);
			
			resp.sendRedirect(req.getContextPath() + "/user/login");
			return;
			
		}

		// 将用户名和密码存入新建的User对象中
		User loginUser = new User();
		loginUser.setUsername(username);
		loginUser.setPassword(password);

		// 获取之后进行判断是否认证
		boolean pass = us.check(loginUser,dbUser);

		/*
		 * 通过之后直接跳转到首页 如果没有通过则返回登录页面
		 */
		if (pass) {
			resp.sendRedirect(req.getContextPath()+"/index");
			req.getSession().setAttribute(Global.LOGIN_USER_KEY, dbUser);
		} else {
			/*
			 * 没有通过，产生错误 因为重定向，只能把错误信息放到session中
			 */
			req.getSession().setAttribute("#login_error", "用户名和密码不匹配，请重新输入");
			
			/*
			 * 当密码输入错误或者其他原因没登陆成功时，用户往往希望能够继续保留刚才输入的用户名
			 */
			req.getSession().setAttribute("#longin_username", loginUser.getUsername());
			
			
			resp.sendRedirect(req.getContextPath() + "/user/login");
		}

	}
	
	private void exists(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String username = req.getParameter("username");
		User user = us.findByUsername(username);
		
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		
		if(user == null) {
			pw.write("{\"error\":\"账户不存在\"}");
			
			
		}else {
			pw.write("{\"success\":\"账户不存在\"}");
		}
		
		pw.flush();
		pw.close();
		
	}
	
	
}
