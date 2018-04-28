package com.mcfish.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mcfish.controller.base.BaseController;
import com.mcfish.controller.base.SystemError;

/**
 * 登录验证过滤器
 * @author ZhouXiaobing
 * @date 2018年3月24日 上午9:05:31 
 * @version 1.0
 */
public class LoginFilter extends BaseController implements Filter {
	
	/**
	 * 初始化
	 */
	public void init(FilterConfig fc) throws ServletException {
		System.out.println("++++++++++  登录过滤器初始化  ++++++++++");
	}
	
	public void destroy() {
		System.out.println("++++++++++  登录过滤器销毁     ++++++++++");
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		HttpSession session = request.getSession();
		String session_share = (String) session.getAttribute("session_share");
		String[] requestUrl = request.getRequestURI().split("/");
		
		if(requestUrl[2].equals("sharecLoginOut.do") || requestUrl[2].equals("login")) {
			chain.doFilter(req, res); // 调用下一过滤器
			return;
		}
		
		int enabled = 0;
		if (session_share != null) {

			String userEdit = (String) session.getAttribute("userEdit");
			@SuppressWarnings("unchecked")
			Map<String, String> MyLookAuthMap = (Map<String, String>) session.getAttribute("MyLookAuthMap");
			
			if(userEdit == null ) {
				response.setContentType("text/html;charset=utf-8");
				response.sendError(SystemError.NOT_AUTHORITY.getCode(), SystemError.NOT_AUTHORITY.getMessage());
				return;
			}
			
			if (userEdit.equals("all") 
					|| requestUrl[3].contains("select") 
					|| requestUrl[3].contains("get")
					|| requestUrl[3].contains("Page")
					|| requestUrl[3].contains("to")) {

				chain.doFilter(req, res); // 调用下一过滤器

			} else {
				// 拥有的权限（菜单的id）
				String hasAuthKey[] = userEdit.split(",");
				
				if(MyLookAuthMap == null) {
					response.setContentType("text/html;charset=utf-8");
					response.sendError(SystemError.NOT_AUTHORITY.getCode(), SystemError.NOT_AUTHORITY.getMessage());
					return;
				}


				for (int i = 0; i < hasAuthKey.length; i++) {
					if(MyLookAuthMap.get(hasAuthKey[i]) != null 
							&& MyLookAuthMap.get(hasAuthKey[i]) != "" 
							&& MyLookAuthMap.get(hasAuthKey[i]).contains(requestUrl[2])) {
						enabled = 1;
					}
				}
				
				if(enabled == 1) {
					enabled = 0;
					chain.doFilter(req, res);
				}else {
					enabled = 0;
					response.setContentType("text/html;charset=utf-8");
					response.sendError(SystemError.NOT_AUTHORITY.getCode(), SystemError.NOT_AUTHORITY.getMessage());
				}
			}

		} else {
			System.out.println("++++++++++  登录过滤器【未通过】，回到登录页面  ++++++++++");
			// 如果检测不到session ，跳转到登录界面
			response.sendRedirect(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/");
		}
	}
}
