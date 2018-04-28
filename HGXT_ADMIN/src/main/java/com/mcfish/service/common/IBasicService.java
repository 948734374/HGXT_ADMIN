package com.mcfish.service.common;

import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mcfish.entity.common.Menu;
import com.mcfish.util.PageData;

/**
 * 公共方法
 * @author ZhouXiaobing
 * @date 2018年3月24日 上午8:34:47 
 * @version 1.0
 */
public interface IBasicService {

	/**
	 * 查看权限菜单
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 上午8:56:25 
	 * @param views
	 * @return
	 * @throws Exception
	 */
	public List<Menu> getMyLookAuth(String views) throws Exception;
	
	/**
	 * 查看编辑权限
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 上午8:56:31 
	 * @param edits
	 * @return
	 * @throws Exception
	 */
	public List<Menu> getMyEditAuth(String edits) throws Exception;
	
	/**
	 * 管理员操作日志
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 上午8:56:40 
	 * @param request
	 * @param comment
	 * @throws Exception
	 */
	public void addOperateLog(HttpServletRequest request, String action, String comment ) throws Exception;
	
	
	/**
	 * 查询登录页关键字
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 上午8:35:00 
	 * @return
	 * @throws Exception
	 */
	public List<Map<String , Object>> getLoginKeyWord() throws Exception;
	

	/**
	 * 获取操作日志
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 上午8:56:55 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> getAllLogs(PageData pd) throws Exception;
	
	
	/**
	 * 获取操作日志总数
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 上午8:57:04 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	 int getAllLogsCount(PageData pd) throws Exception;

	 
	 /**
	  * 获取系统所有菜单，管理员设置模块中设置权限时用
	  * @author ZhouXiaobing 
	  * @date 2018年3月27日 下午3:25:16 
	  * @return
	 * @throws Exception 
	  */
	public List<Menu> getAllMenu() throws Exception;
}
