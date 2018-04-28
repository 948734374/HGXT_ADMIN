package com.mcfish.service.common;

import java.util.List;

import com.mcfish.entity.common.User;
import com.mcfish.util.PageData;

/**
 * 用户管理service
 * @author ZhouXiaobing
 * @date 2018年3月29日 下午3:14:18 
 * @version 1.0
 */
public interface IUserService {
	
	/**
	 * 根据条件获取所有用户信息
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 下午3:58:02 
	 * @param pageData
	 * @return
	 * @throws Exception
	 */
	List<User> getAllUserList(PageData pageData) throws Exception;
	
	/**
	 * 根据用户ID查找用户
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 下午4:50:08 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	User getUserById(int userId) throws Exception;
	
	
	/**
	 * 更新用户基本信息
	 * @author TangLin 
	 * @date 2017年7月17日 下午6:15:58 
	 * @param   
	 * @return void
	 */
	void updateUser(PageData pageData) throws Exception;
	
	
	/**
	 * 更改用户状态
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 下午4:05:32 
	 * @param pageData
	 * @throws Exception
	 */
	void updateUserStatus(PageData pageData) throws Exception;
	

	/**
	 * 根据用户id删除
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 下午4:59:10 
	 * @param id
	 * @throws Exception
	 */
	void deleteUser(int id) throws Exception;
	
}
