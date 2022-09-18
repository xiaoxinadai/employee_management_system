package com.situ.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 注销后到达的页面
 * 首页
 * @author adai
 *
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet{
	private static final long serialVersionUID = -3927829412692738531L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
		
	}
	
	
	
}
