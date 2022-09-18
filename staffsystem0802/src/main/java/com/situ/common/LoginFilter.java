package com.situ.common;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.situ.model.User;
import com.situ.utils.Pair;

/**
 * 登录过滤器 拦截一切请求，个别手动放行
 * 
 * @author adai
 *
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

	/*
	 * 定义一个集合来存放所有要过滤的url
	 */
	private final Set<Pair<String,Matcher>> ignoreUrls = new HashSet<>();

	/*
	 * 定义无参构造函数 服务器使用反射调用的就是空的无参构造函数
	 */
	public LoginFilter() {
		ignoreUrls.add(Pair.of("/user/login", Matcher.EQUALS));
		ignoreUrls.add(Pair.of("/user/exists", Matcher.EQUALS));
		ignoreUrls.add(Pair.of("/user/logout", Matcher.EQUALS));
		ignoreUrls.add(Pair.of(".css", Matcher.ENDS_WITH));
		ignoreUrls.add(Pair.of(".js",  Matcher.ENDS_WITH));
		ignoreUrls.add(Pair.of(".png",  Matcher.ENDS_WITH));
		ignoreUrls.add(Pair.of(".jpg",  Matcher.ENDS_WITH));
		ignoreUrls.add(Pair.of(".webp",  Matcher.ENDS_WITH));
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		// 从session中获取一些数据，通常情况为用户数据。因此需要创建一个User
		HttpSession session = req.getSession();

		String uri = req.getRequestURI();
		// uri去掉req.getServletPath
		String path = uri.replaceAll(req.getContextPath(), "");
		
		
		/*
		 * 增强for取出值，进行判断
		 */
		boolean match = false;
		for(Pair<String, Matcher> pair : ignoreUrls) {
			String k = pair.getFirst();
			Matcher v = pair.getSecond();
			
			
			
			if(v == Matcher.EQUALS) {
				//这种情况下，要判断k是否等于path
				match = path.equals(k);
			}else if(v == Matcher.ENDS_WITH) {
				match = path.endsWith(k);
				
			}
			if(match) {
				break;
			}
		}
		
		// 通过上面几步获取到了/user/login
		if (match) {// 直接放行
			chain.doFilter(request, response);
		} else {
			// 从session对话中获取loginUser
			User loginUser = (User) session.getAttribute(Global.LOGIN_USER_KEY);
			// loginUser存在为空的可能,未登录的情况
			if (loginUser == null) {
				// 需要重定向到login，进行登录
				resp.sendRedirect(req.getContextPath() + "/user/login");
			} else {
				// 不为空，则使用者输入了用户名和密码进行了登录操作，则放行。没有登录则不放行
				chain.doFilter(request, response);
			}
		}
	}

	// 枚举
	// 创建一个内部类 匹配器
	public static enum Matcher {
		EQUALS, START_WITH, ENDS_WITH, CONTAINS
	}

}
