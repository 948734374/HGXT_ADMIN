package com.mcfish.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.mcfish.controller.base.BaseController;

/**
 * 处理ajax跨域问题
 * @author ZhouXiaobing
 * @date 2018年3月24日 上午9:04:50 
 * @version 1.0
 */
public class CrossDamainFilter extends BaseController implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("++++++++++   跨域过滤器初始化  ++++++++++");
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    chain.doFilter(req, res);
	}

	public void destroy() {
		System.out.println("++++++++++ 跨域过滤器销毁  ++++++++++");
	}
}
