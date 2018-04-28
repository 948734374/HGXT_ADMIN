package com.mcfish.service.common.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.mcfish.dao.DaoSupport;
import com.mcfish.entity.common.Menu;
import com.mcfish.entity.common.OperateLog;
import com.mcfish.service.common.IBasicService;
import com.mcfish.util.PageData;
@Service("basicServiceImpl")
public class BasicServiceImpl implements IBasicService {

	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	//查看权限菜单
	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> getMyLookAuth(String views) throws Exception {
		List<Menu> menuList = new ArrayList<Menu>();
		if (views != null) {
			if (views.equals("all")) {
				menuList =(List<Menu>) dao.findForList("BasicMapper.getAllMenuList", null);
			}else{
				  String[] viewsArray = views.split(",");
				menuList = (List<Menu>) dao.findForList("BasicMapper.getMenuList", viewsArray);
			}
		}
		return menuList;
	}
	
	
	//查看编辑权限
	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> getMyEditAuth(String edits) throws Exception {
		List<Menu> menuList = new ArrayList<Menu>();
		if (edits != null) {
			if (edits.equals("all")) {
				menuList =(List<Menu>) dao.findForList("BasicMapper.getAllMenuList", null);
			}else{
				  String[] editsArray = edits.split(",");
				menuList = (List<Menu>) dao.findForList("BasicMapper.getMenuList", editsArray);
			}
		}
		return menuList;
	}
	
	
	//添加管理员操作日志
	@Override
	public void addOperateLog(HttpServletRequest request, String action, String comment) throws Exception {
		OperateLog log = new OperateLog();
		
		//获取请求ip
		String ip = getIpAddr(request);
		//理员管名称
		String username = (String) request.getSession().getAttribute("username");
		int adminId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
		
		log.setIp(ip);
		log.setAction(action);
		log.setAdmin_id(adminId);
		log.setCreator(username);
		log.setComment(comment);
		
		dao.save("BasicMapper.addOperateLog", log);
	}

	/**
	 * 获取ip   如果服务器使用nginx做反向代理 ，需要配置nginx的nginx.conf才能获取真实ip
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午3:26:22 
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}


	//查询登录页关键字
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getLoginKeyWord() throws Exception {
		return (List<Map<String, Object>>) dao.findForList("BasicMapper.getLoginKeyWord", null);
	}


	//获取操作日志
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getAllLogs(PageData pd) throws Exception {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		pd.put("start", Integer.parseInt( pd.get("start").toString() ) );
		pd.put("length", Integer.parseInt( pd.get("length").toString() ) );
		
		List<OperateLog> logList = (List<OperateLog>) dao.findForList("BasicMapper.getAllLogs", pd);
		int logTotal = getAllLogsCount(pd);
		
		map.put("data", logList);
		map.put("recordsTotal", logTotal);   //dataTable分页需要
		map.put("recordsFiltered", logTotal);//dataTable分页需要
		map.put("draw", pd.get("draw").toString());//dataTable分页需要
		
		return map;
	}


	//获取操作日志总数
	@Override
	public int getAllLogsCount(PageData pd) throws Exception {
		return (int) dao.findForObject("BasicMapper.getAllLogsCount", pd);
	}


	//获取系统所有菜单，管理员设置模块中设置权限时用
	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> getAllMenu() throws Exception {
		return (List<Menu>) dao.findForList("BasicMapper.getAllMenu", null);
	}
	
	
}
