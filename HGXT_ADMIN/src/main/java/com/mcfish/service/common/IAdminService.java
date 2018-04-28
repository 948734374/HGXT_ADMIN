package com.mcfish.service.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mcfish.entity.common.Admin;
import com.mcfish.entity.common.Agent;
import com.mcfish.util.PageData;


public interface IAdminService {
	
	
	/**
	 * 获取管理员列表
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 上午8:40:02 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	List<Admin> getAdminList(PageData pd) throws Exception;
	

	/**
	 * 通过用户账号获取账号信息
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 上午8:42:19 
	 * @param account
	 * @return
	 * @throws Exception
	 */
	Admin getUserByUserAccount(String account) throws Exception;
	
	
	/**
	 * 渠道上登录验证 
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 上午8:42:30 
	 * @param username
	 * @return
	 * @throws Exception
	 */
	Agent getAgentByPhone(String account) throws Exception;


	/**
	 * 添加管理员
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 上午10:40:13 
	 * @param pd
	 * @return 
	 * @throws Exception
	 */
	Object addAdmin(PageData pd) throws Exception;

	
	/**
	 * 根据ID重置密码
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 上午11:10:46 
	 * @param pd
	 * @throws Exception
	 */
	void updatePwd(PageData pd)  throws Exception;

	
	/**
	 * 启用/停用账号
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 上午11:21:15 
	 * @param pd
	 * @throws Exception
	 */
	void updateStatus(PageData pd) throws Exception;

	
	/**
	 * 修改管理员信息
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 上午11:24:34 
	 * @param pd
	 * @throws Exception
	 */
	void updateById(PageData pd) throws Exception;

	
	/**
	 * 根据管理员id查询管理员信息
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 上午11:44:35 
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	Admin getAdminById(Integer id) throws Exception;


	/**
	 * 根据用户账号修改密码
	 * @author ZhouXiaobing 
	 * @date 2018年3月31日 下午2:30:46 
	 * @param pd
	 * @param request
	 * @throws Exception
	 */
	Object updateAdminPassword(PageData pd, HttpServletRequest request) throws Exception;

	
	/**
	 * 删除管理员
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 上午11:16:31 
	 * @param id
	 * @throws Exception
	 */
	void deleteAdmin(int id) throws Exception;

	
	/**
	 * 权限保存
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 上午11:31:16 
	 * @param pd
	 * @throws Exception
	 */
	void updateAuthStr(PageData pd) throws Exception;

}
