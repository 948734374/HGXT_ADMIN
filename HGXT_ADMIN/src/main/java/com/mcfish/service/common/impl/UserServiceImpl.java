package com.mcfish.service.common.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mcfish.dao.DaoSupport;
import com.mcfish.entity.common.User;
import com.mcfish.service.common.IUserService;
import com.mcfish.util.PageData;

/**
 * @author ZhouXiaobing
 * @date 2018年3月29日 下午3:14:37 
 * @version 1.0
 */
@Service ("userServiceImpl")
public class UserServiceImpl implements IUserService{
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;

	
	//根据条件获取所有用户信息
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUserList(PageData pd) throws Exception {
		
		pd.put("start", Integer.parseInt(pd.get("start").toString()));
		pd.put("length", Integer.parseInt(pd.get("length").toString()));
		pd.put("keyword", pd.get("search[value]").toString());
		
		return (List<User>) dao.findForList("UserMapper.getAllUserList", pd);
	}


	//根据用户ID查找用户
	@Override
	public User getUserById(int userId) throws Exception {
		return  (User) dao.findForObject("UserMapper.getUserById",userId);
	}

	
	//更新用户基本信息
	@Override
	public void updateUser(PageData pageData) throws Exception {
		dao.update("UserMapper.updateUser", pageData);
		
	}

	
	//更改用户状态
	@Override
	public void updateUserStatus(PageData pageData) throws Exception {
		dao.update("UserMapper.updateUserStatus", pageData);
	}

	
	//根据用户id删除
	@Override
	public void deleteUser(int id) throws Exception {
		dao.delete("UserMapper.deleteUser", id);
	}
	
}
