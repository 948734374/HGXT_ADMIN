package com.mcfish.service.common.impl;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import com.mcfish.controller.base.InterfaceResult;
import com.mcfish.controller.base.SystemError;
import com.mcfish.dao.DaoSupport;
import com.mcfish.entity.common.Admin;
import com.mcfish.entity.common.Agent;
import com.mcfish.service.common.IAdminService;
import com.mcfish.util.MD5;
import com.mcfish.util.PageData;

@Service("adminServiceImpl")
public class AdminServiceImpl implements IAdminService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	//获取管理员列表
	@SuppressWarnings("unchecked")
	@Override
	public List<Admin> getAdminList(PageData pd) throws Exception {
		
		pd.put("start", Integer.parseInt(pd.get("start").toString()));
		pd.put("length", Integer.parseInt(pd.get("length").toString()));
		pd.put("keyword", pd.get("search[value]").toString());
		
		return (List<Admin>) dao.findForList("AdminMapper.getAdminList", pd);
	}
	
	
	//通过用户账号获取账号信息
	@Override
	public Admin getUserByUserAccount(String account) throws Exception {
		return (Admin) dao.findForObject("AdminMapper.getUserByUserAccount", account);
	}

	
	//添加管理员
	@Override
	public Object addAdmin(PageData pd) throws Exception {
		
		String account = pd.get("account").toString();
		
		Admin admin = this.getUserByUserAccount(account);
		if (admin != null) {
			return InterfaceResult.returnFailureWithMsg("账号已经存在");
		}
		
		Admin admin2 = new Admin();
		//为新的管理员添加角色
		int roleId = (int) dao.save("AdminMapper.addNewAdminRole", admin2);
		
		if(roleId != 1){
			return InterfaceResult.returnFailureWithMsg("添加失败，请稍后再试");
		}
		
		pd.put("role_id", admin2.getRole_id());
		pd.put("password", MD5.pwdMd5("123456"));
		dao.save("AdminMapper.addAdmin", pd);
		
		return InterfaceResult.returnSuccess(null);
	}

	
	//根据ID重置密码
	@Override
	public void updatePwd(PageData pd) throws Exception {
		pd.put("password", MD5.pwdMd5("123456"));
		dao.update("AdminMapper.updatePwd", pd);
	}

	
	//启用/停用账号
	@Override
	public void updateStatus(PageData pd) throws Exception {
		dao.update("AdminMapper.updateStatus", pd);
	}

	
	//修改管理员信息
	@Override
	public void updateById(PageData pd) throws Exception {
		dao.update("AdminMapper.updateById", pd);
	}

	
	//根据管理员id查询管理员信息
	@Override
	public Admin getAdminById(Integer id) throws Exception {
		return (Admin) dao.findForObject("AdminMapper.getAdminById", id);
	}


	//根据用户账号修改密码
	@Override
	public Object updateAdminPassword(PageData pd, HttpServletRequest request) throws Exception {
		
		String account = (String) request.getSession().getAttribute("account");
		String oldPassword = pd.get("oldPassword").toString();
		String newPassword = pd.get("newPassword").toString();
		
		Admin admin = this.getUserByUserAccount(account);
		
		if(null != admin && !admin.getPassword().equals(MD5.pwdMd5(oldPassword))){
			
			return InterfaceResult.returnResult(
					SystemError.OLD_FAILED_PASSWORD.getCode(), 
					SystemError.OLD_FAILED_PASSWORD.getMessage(), null);
		}
		
		admin.setPassword(MD5.pwdMd5(newPassword));
		
		dao.update("AdminMapper.updateAdminPassword", admin);
		
		return InterfaceResult.returnSuccess(null);
	}

	
	//删除管理员
	@Override
	public void deleteAdmin(int id) throws Exception {
		dao.delete("AdminMapper.deleteAdmin",id);
	}

	
	//权限保存
	@Override
	public void updateAuthStr(PageData pd) throws Exception {
		dao.update("AdminMapper.updateAuthStr", pd);
	}

	
	//渠道上登录验证 
	@Override
	public Agent getAgentByPhone(String account) throws Exception {
		return (Agent) dao.findForObject("AdminMapper.getAgentByPhone", account);
	}

}
