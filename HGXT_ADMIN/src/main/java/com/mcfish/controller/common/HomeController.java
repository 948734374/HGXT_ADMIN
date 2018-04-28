package com.mcfish.controller.common;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcfish.controller.base.BaseController;
import com.mcfish.controller.base.InterfaceResult;
import com.mcfish.entity.common.Home;
import com.mcfish.service.common.IHomeService;
import com.mcfish.util.PageData;


/**
 * 平台首页管理控制层
 * @author ZhouXiaobing
 * @date 2018年3月28日 下午1:35:37 
 * @version 1.0
 */
@Controller
@RequestMapping(value="/shareHomeController")
public class HomeController extends BaseController{
	
	@Resource(name = "homeServiceImpl")
	private IHomeService homeServiceImpl;
	
	
	/**
	 * 跳转到平台首页
	 * @author ZhouXiaobing 
	 * @date 2018年3月28日 下午1:36:22 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/HomePage.do")
	public ModelAndView toHomePage()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		
		mv.setViewName("common/home/home");
		mv.addObject("pd",pd);
		
		return mv;
	}
	
	
	/**
	 * 获取平台头部数据信息 
	 * @author ZhouXiaobing 
	 * @date 2018年3月28日 下午1:37:29 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getHomeData.do")
	public Object getHomeData() throws Exception {
		
		Home home = homeServiceImpl.getHomeData();
		
		return InterfaceResult.returnSuccess(home);
	}
	

	/**
	 * 本周每日用户数量
	 * @author ZhouXiaobing 
	 * @date 2018年3月28日 下午3:56:28 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getEverydayUser.do")
	public Object getEverydayUser() throws Exception {
		PageData pd = new PageData();
		
		List<Map<String,Object>> userEverydayCount = homeServiceImpl.getEverydayUser(pd);
	
		return InterfaceResult.returnSuccess(userEverydayCount);
	}
	
	
	/**
	 * 本周每日订单数量
	 * @author ZhouXiaobing 
	 * @date 2018年3月28日 下午4:00:29 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getEverydayOrder.do")
	public Object getEverydayOrder() throws Exception {
		PageData pd = new PageData();
		
		List<Map<String,Object>> orderEverydayCount = homeServiceImpl.getEverydayOrder(pd);
	
		return InterfaceResult.returnSuccess(orderEverydayCount);
	}
}


