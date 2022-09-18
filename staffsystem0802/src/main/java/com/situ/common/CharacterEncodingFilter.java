package com.situ.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 中文编码过滤器
 */
@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
        throws IOException, ServletException {
    	
    	//类型转换
        HttpServletRequest req = (HttpServletRequest)arg0;
        HttpServletResponse resp = (HttpServletResponse)arg1;
        
        
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        
        //操作后直接放行
        chain.doFilter(arg0, arg1);
    }
}
