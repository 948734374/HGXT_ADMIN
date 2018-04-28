package com.mcfish.controller.login;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcfish.controller.base.BaseController;
import com.mcfish.controller.base.InterfaceResult;
import com.mcfish.entity.common.Admin;
import com.mcfish.entity.common.Agent;
import com.mcfish.entity.common.Menu;
import com.mcfish.service.common.IAdminService;
import com.mcfish.service.common.IBasicService;
import com.mcfish.util.MD5;
import com.mcfish.util.PageData;
import com.mcfish.util.UuidUtil;

/**
 * 登录控制
 * @author ZhouXiaobing
 * @date 2018年3月24日 上午8:22:54 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "")
public class LoginController extends BaseController {

	@Resource(name = "adminServiceImpl")
	private IAdminService adminServiceImpl;

	@Resource(name = "basicServiceImpl")
	private IBasicService basicServiceImpl;

	
	/**
	 * 访问登录界面
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 上午8:23:07 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tologin")
	public ModelAndView tologin() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		
		mv.setViewName("login");
		mv.addObject("pd", pd);
		
		return mv;
	}

	
	/**
	 * 查询登录页关键字
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 上午8:23:30 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/keyValue")
	public Object getLoginKeyWord() throws Exception {
		List<Map<String, Object>> list = basicServiceImpl.getLoginKeyWord();
		return InterfaceResult.returnSuccess(list);
	}

	
	/**
	 * 管理员登录验证
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 上午8:23:43 
	 * @param username
	 * @param password
	 * @param types
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam(required = false) String account,
			@RequestParam(required = false) String password, @RequestParam(required = false) String types,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mv = this.getModelAndView();

		// 在session中加入参数
		HttpSession session = request.getSession();
		String session_share = UuidUtil.get32UUID();

		mv.setViewName("login");
		mv.addObject("account", account);
		PageData pd = this.getPageData();

		// 处理已经进入首页后刷新 当前 地址栏
		if (account == null || account == "") {
			if (session.getAttribute("session_share") == null || session.getAttribute("session_share").equals("")) {
				mv.setViewName("login");
			} else {
				mv.setViewName("shareindex");
			}
			return mv;
		}

		// 系统登录
		if (types.equals("1")) {
			
			Admin user = adminServiceImpl.getUserByUserAccount(account);

			if (user == null || !user.getPassword().equals(MD5.pwdMd5(password))) {
				mv.addObject("Msg", "用户名或密码错误");
				return mv;
			}
			if (user.getStatus() == 1) {
				mv.addObject("Msg", "您的账号已被冻结");
				return mv;
			}

			/************************ session管理开始 ******************************/
			user.setPassword("***");
			session.setAttribute("user", user);
			session.setAttribute("username", user.getName());
			session.setAttribute("session_share", session_share);
			session.setAttribute("account", account);
			session.setAttribute("userId", user.getId());
			session.setAttribute("userEdit", user.getEdits());

			// 把自己的权限查询出来，放在session里，包括 查看权限和 修改权限
			// 查看权限
			List<Menu> MyLookAuth = basicServiceImpl.getMyLookAuth(user.getViews());
			Map<String, String> MyLookAuthMap = new HashMap<String, String>();
			for (int i = 0; i < MyLookAuth.size(); i++) {
				MyLookAuthMap.put(MyLookAuth.get(i).getId().toString(), MyLookAuth.get(i).getLink());
			}
			session.setAttribute("MyLookAuth", MyLookAuth);
			session.setAttribute("MyLookAuthMap", MyLookAuthMap);

			//获取系统所有菜单，管理员设置模块中设置权限时用
			List<Menu> allMenu = basicServiceImpl.getAllMenu();
			session.setAttribute("allMenu", allMenu);
			/************************ session管理结束 ******************************/
		}

		// 渠道登录
		if (types.equals("2")) {
			Agent agent = adminServiceImpl.getAgentByPhone(account);

			if (agent == null || !agent.getPassword().equals(MD5.pwdMd5(password))) {
				mv.addObject("Msg", "用户名或密码错误");
				return mv;
			}
			if (agent.getStatus() == 1) {
				mv.addObject("Msg", "您的账号已被冻结");
				return mv;
			}

			// 渠道商登录能够看到自己的名字
			Admin user = new Admin();
			user.setName(agent.getName());

			/************************ session管理开始 ******************************/
			agent.setPassword("***");
			session.setAttribute("agent", agent);
			session.setAttribute("user", user);
			session.setAttribute("session_share", session_share);
			session.setAttribute("account", account);
			session.setAttribute("agentId", agent.getId());
			session.setAttribute("proportion", agent.getProportion());
			session.setAttribute("phone", agent.getPhone());
			session.setAttribute("level", agent.getLevel());
			session.setAttribute("agentName", agent.getName());

			// 把自己的权限查询出来，放在session里，包括 查看权限和 修改权限
			// 可查看权限(商家、小二、渠道)
			String views = "105,106,103,104,120";
			List<Menu> MyLookAuth = basicServiceImpl.getMyLookAuth(views);
			Map<String, String> MyLookAuthMap = new HashMap<String, String>();
			for (int i = 0; i < MyLookAuth.size(); i++) {
				MyLookAuthMap.put(MyLookAuth.get(i).getId().toString(), MyLookAuth.get(i).getLink());
			}
			session.setAttribute("MyLookAuth", MyLookAuth);
			session.setAttribute("MyLookAuthMap", MyLookAuthMap);
		}

		// 记住密码 ,标记等于1则设置cookie
		String remFlag = request.getParameter("remFlag");
		if ("1".equals(remFlag)) {
			String loginInfo = account + "," + password;
			Cookie userCookie = new Cookie("loginInfo", loginInfo);
			userCookie.setMaxAge(30 * 24 * 60 * 60); // 存活期为一个月 30*24*60*60
			userCookie.setPath("/");
			response.addCookie(userCookie);
		} else {
			// 删除cookie
			Cookie cookie = new Cookie("loginInfo", null);
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		mv.setViewName("shareindex");
		return mv;
	}
	

	/**
	 * 管理员修改密码
	 * @author ZhouXiaobing 
	 * @date 2018年3月31日 下午1:49:21 
	 * @param oldPassword
	 * @param newPassword
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/shareChangePassword.do")
	public Object hrsscChangePassword(@RequestParam(required = true) String oldPassword,
			@RequestParam(required = true) String newPassword, HttpServletRequest request) throws Exception {
		PageData pd = this.getPageData();
		
		return adminServiceImpl.updateAdminPassword(pd, request);
		
	}

	/**
	 * 退出登录
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 上午8:26:48 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "/sharecLoginOut.do")
	public Object hrsscLoginOut(HttpServletRequest request) {
		// 清空session
		HttpSession session = request.getSession();
		Enumeration em = request.getSession().getAttributeNames();
		while (em.hasMoreElements()) {
			session.removeAttribute(em.nextElement().toString());
		}
		return InterfaceResult.returnSuccess(null);
	}
}
