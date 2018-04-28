package com.mcfish.controller.common;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcfish.controller.base.BaseController;
import com.mcfish.controller.base.InterfaceResult;
import com.mcfish.entity.common.User;
import com.mcfish.service.common.IUserService;
import com.mcfish.util.PageData;

/**
 * 用户管理控制层
 * @author ZhouXiaobing
 * @date 2018年3月24日 上午11:33:07
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/shareUserController")
public class UserController extends BaseController {

	@Resource(name = "userServiceImpl")
	private IUserService userServiceImpl;

	
	/**
	 * 跳转到用户管理页面
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 上午11:33:22 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/UserPage.do")
	public ModelAndView toUserPage() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		
		mv.setViewName("common/user/user");
		mv.addObject("pd", pd);
		
		return mv;
	}

	
	/**
	 * 跳转到用户详情页面
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 上午11:38:06 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toUserDetail.do")
	public ModelAndView toUserDetail() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		
		mv.setViewName("common/user/detail");
		mv.addObject("pd", pd);
		
		return mv;
	}
	

	/**
	 * 根据条件获取所有用户信息列表
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 下午1:56:27 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getAllUserList.do")
	public Object getAllUserList() throws Exception {
		PageData pd = this.getPageData();
		
		List<User> userList = userServiceImpl.getAllUserList(pd);
		Long userTotal = userList.size() == 0 ? 0l:userList.get(0).getTotal();
		
		return InterfaceResult.returnTableSuccess(userList, userTotal, pd.get("draw"));
	}
	
	
	/**
	 * 更新用户状态
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 下午2:02:34 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/updateUserStatus.do", method = RequestMethod.POST)
	public Object updateUserStatus() throws Exception {
		PageData pd = this.getPageData();
		
		userServiceImpl.updateUserStatus(pd);
		
		return InterfaceResult.returnSuccess(null);
	}
	
	
	/**
	 * 根据用户id删除
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 下午2:02:41 
	 * @param request
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/deleteUser.do")
	public Object deleteUser(HttpServletRequest request, 
								@RequestParam(required = true) int userId) throws Exception {
		
		userServiceImpl.deleteUser(userId);
		
		return InterfaceResult.returnSuccess(null);
	}
	

	/**
	 * 根据用户ID查找用户详细资料
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 下午2:02:09 
	 * @param request
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getUserById.do", method = RequestMethod.GET)
	public Object getUserById(HttpServletRequest request, 
								@RequestParam(required = true) int uid) throws Exception {
		
		User user = userServiceImpl.getUserById(uid);
		
		return InterfaceResult.returnSuccess(user);
	}


	/**
	 * 更新用户基本信息
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 下午2:02:26 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/updateUserInfo.do", method = RequestMethod.POST)
	public Object updateUserInfo() throws Exception {
		PageData pd = this.getPageData();

		userServiceImpl.updateUser(pd);

		return InterfaceResult.returnSuccess(null);
	}

}
