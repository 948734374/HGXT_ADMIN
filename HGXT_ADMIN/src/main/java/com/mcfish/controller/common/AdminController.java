package com.mcfish.controller.common;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcfish.controller.base.BaseController;
import com.mcfish.controller.base.InterfaceResult;
import com.mcfish.entity.common.Admin;
import com.mcfish.service.common.IAdminService;
import com.mcfish.service.common.IBasicService;
import com.mcfish.util.PageData;


/**
 * 管理员配置
 * @author ZhouXiaobing
 * @date 2018年3月26日 下午6:23:32
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/shareAdminController")
public class AdminController extends BaseController {

	@Resource(name = "adminServiceImpl")
	private IAdminService adminServiceImpl;

	@Resource(name = "basicServiceImpl")
	private IBasicService basicServiceImpl;

	
	/**
	 * 访问管理员配置页面
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 上午8:29:57 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/AdminPage.do")
	public ModelAndView toAdminPage() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		
		mv.setViewName("common/admin/admin");
		mv.addObject("pd", pd);
		
		return mv;
	}

	
	/**
	 * 获取管理员列表
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 上午8:30:22 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getAdminList.do")
	public Object getAdminList() throws Exception {
		PageData pd = this.getPageData();

		List<Admin> adminList = adminServiceImpl.getAdminList(pd);
		Long recordsTotal = adminList.size() == 0 ? 0l:adminList.get(0).getTotal();

		return InterfaceResult.returnTableSuccess(adminList, recordsTotal, pd.get("draw"));
	}
	

	/**
	 * 添加管理员
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 上午11:08:15 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/addAdmin.do")
	public Object addAdmin() throws Exception {
		PageData pd = this.getPageData();
		
		return adminServiceImpl.addAdmin(pd);
	}
	

	/**
	 * 重置密码为123456
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 上午11:08:24 
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/updatePwd.do")
	public Object updatePwd() throws Exception {
		PageData pd = this.getPageData();
		
		adminServiceImpl.updatePwd(pd);
		
		return InterfaceResult.returnSuccess(null);
	}

	
	/**
	 * 删除管理员
	 * 因数据库设置，目前采用当前方案
	 * @param id role_id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteAdmin.do")
	public Object deleteAdmin(@RequestParam("id") int id) throws Exception {
		
		adminServiceImpl.deleteAdmin(id);
		
		return InterfaceResult.returnSuccess(null);
	}

	
	/**
	 * 启用/停用账号
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 上午11:20:08 
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/updateStatus.do")
	public Object updateStatus() throws Exception {
		PageData pd = this.getPageData();
		
		adminServiceImpl.updateStatus(pd);
		
		return InterfaceResult.returnSuccess(null);
	}

	
	/**
	 * 修改管理员信息
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 上午11:23:17 
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/updateById.do")
	public Object updateById() throws Exception {
		PageData pd = this.getPageData();
		
		adminServiceImpl.updateById(pd);
		
		return InterfaceResult.returnSuccess(null);
	}
	

	/**
	 * 权限保存
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 上午11:30:30 
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/updateAuth.do")
	public Object updateAuth() throws Exception {
		PageData pd = this.getPageData();
		
		adminServiceImpl.updateAuthStr(pd);
		
		return InterfaceResult.returnSuccess(null);
	}

	
	/**
	 * 根据管理员id查询管理员信息
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 上午11:37:04 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getAdminById.do")
	public Object getAdminInfo(@RequestParam("id") Integer id) throws Exception {
		
		Admin admin = adminServiceImpl.getAdminById(id);
		
		return InterfaceResult.returnSuccess(admin);
	}
}
