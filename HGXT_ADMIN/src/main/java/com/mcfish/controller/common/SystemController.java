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
import com.mcfish.entity.common.About;
import com.mcfish.entity.common.Charge;
import com.mcfish.entity.common.System;
import com.mcfish.entity.common.Version;
import com.mcfish.service.common.IBasicService;
import com.mcfish.service.common.ISystemService;
import com.mcfish.util.PageData;

/**
 * 系统设置
 * @author ZhouXiaobing
 * @date 2018年3月27日 下午4:22:23
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/shareSystemController")
public class SystemController extends BaseController {

	@Resource(name = "systemServiceImpl")
	private ISystemService systemServiceImpl;

	@Resource(name = "basicServiceImpl")
	private IBasicService basicServiceImpl;

	
	/**
	 * 访问系统配置页面
	 * @author ZhouXiaobing
	 * @date 2018年3月27日 下午4:30:43
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/SystemPage.do")
	public ModelAndView toSystemPage() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		
		mv.addObject(pd);
		mv.setViewName("common/system/system");
		
		return mv;
	}

	
	/**
	 * 跳转到关于我们新增页面
	 * @author ZhouXiaobing 
	 * @date 2018年3月28日 上午9:29:54 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toAddAboutPage.do")
	public ModelAndView toAddAboutPage() throws Exception {
		ModelAndView mv = this.getModelAndView();
		
		mv.setViewName("common/system/bulit");
		
		return mv;
	}

	
	/**
	 * 获取配置列表数据
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午4:31:42 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getConfigList.do")
	public Object getConfigList() throws Exception {
		PageData pd = this.getPageData();
		
		List<System> list = systemServiceImpl.selectAllList(pd);
		Long recordsTotal = list.size() == 0 ? 0l:list.get(0).getTotal();
		
		return InterfaceResult.returnTableSuccess(list, recordsTotal, pd.get("draw"));
	}


	/**
	 * 根据ID查配置信息
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午4:32:00 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getConfigById.do")
	public Object getConfigById(@RequestParam(required = true) int id) throws Exception {

		System config = systemServiceImpl.selectSystemConfigByID(id);
		
		return InterfaceResult.returnSuccess(config);
	}
	
	
	/**
	 * 修改基本配置信息
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午6:31:55 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/updateSystemConfig.do")
	public Object updateSystemConfig() throws Exception {
		PageData pd = this.getPageData();
		
		systemServiceImpl.updateSystemConfigByID(pd);
		
		return InterfaceResult.returnSuccess(null);
	}
	
	
	/**
	 * 添加一条系统配置记录
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午6:24:28 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/addSystemConfig.do")
	public Object addSystemConfig() throws Exception {
		PageData pd = this.getPageData();
		
		return systemServiceImpl.insertSystemConfig(pd);
		
	}
	
	
	/**
	 * 删除一条基础配置
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午8:16:13 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteSystemConfigById.do")
	public Object deleteSystemConfig(@RequestParam(required = true) int id) throws Exception {
		
		systemServiceImpl.deleteSystemConfigByID(id);
		
		return InterfaceResult.returnSuccess(null);
	}
	
	
	/**
	 * 查询所有充值配置
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午7:57:55 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getAllChargeList.do")
	public Object getAllChargeList() throws Exception {
		PageData pd = this.getPageData();
		
		List<Charge> list = systemServiceImpl.selectAllCharge(pd);
		Long recordsTotal = list.size() == 0 ? 0l:list.get(0).getTotal();

		return InterfaceResult.returnTableSuccess(list, recordsTotal, pd.get("draw"));
	}
	
	
	/**
	 * 根据充值id查询充值配置
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午8:05:33 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getChargeById.do")
	public Object getChargeById(Integer id) throws Exception {

		Charge charge = systemServiceImpl.getChargeById(id);

		return InterfaceResult.returnSuccess(charge);
	}
	
	
	/**
	 * 更新充值配置
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午8:08:05 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/updateSystemCharge.do")
	public Object updateSystemCharge() throws Exception {
		PageData pd = new PageData();
		
		systemServiceImpl.updateSystemCharge(pd);
		
		return InterfaceResult.returnSuccess(null);
	}
	
	
	/**
	 * 获取所有app版本信息列表
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午8:20:08 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getAppVersionList.do")
	public Object getAppVersionList() throws Exception {
		
		//TODO 这里需要查询硬件的APP表，开发时，注意切换
		
		PageData pd = this.getPageData();
		
		List<Version> list = systemServiceImpl.selectAllApp(pd);
		Long recordsTotal = list.size() == 0 ? 0l:list.get(0).getTotal();
		
		return InterfaceResult.returnTableSuccess(list, recordsTotal, pd.get("draw"));
	}
	
	
	/**
	 * 修改app版本调试开关
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午8:33:02 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/updateDebug.do")
	public Object updateDebug() throws Exception {
		PageData pd = this.getPageData();
		
		systemServiceImpl.updateDebug(pd);
		
		return InterfaceResult.returnSuccess(null);
	}
	
	
	/**
	 * 添加App版本
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午9:02:05 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/addAppVersion.do")
	public Object addAppVersion() throws Exception {
		PageData pd = this.getPageData();
		
		systemServiceImpl.addAppVersion(pd);
		
		return InterfaceResult.returnSuccess(null);
	}
	
	
	/**
	 * 固件升级
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午9:04:44 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/updateAppVersion.do")
	public Object updateAppVersion() throws Exception {
		PageData pd = this.getPageData();
		
		systemServiceImpl.updateAppVersion(pd);
		
		return InterfaceResult.returnSuccess(null);
	}
	

	/**
	 * 获取所有关于我们信息列表
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午9:18:34 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getAllAboutList.do")
	public Object getAllAboutList() throws Exception {
		PageData pd = this.getPageData();
		
		List<About> list = systemServiceImpl.getAllAbout(pd);
		Long Total = list.size() == 0 ? 0l:list.get(0).getTotal();
		
		return InterfaceResult.returnTableSuccess(list, Total, pd.get("draw"));
	}

	
	/**
	 * 根据id查询关于我们信息
	 * @author ZhouXiaobing 
	 * @date 2018年3月28日 上午11:03:50 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getAboutById.do")
	public Object getAboutById(Integer id) throws Exception {
		
		About about = systemServiceImpl.getAboutById(id);
		
		return InterfaceResult.returnSuccess(about);
	}


	/**
	 * 根据id更改关于我们信息
	 * @author ZhouXiaobing 
	 * @date 2018年3月28日 上午11:06:18 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/updateSystemAbout.do")
	public Object updateSystemAbout() throws Exception {
		PageData pd = this.getPageData();
		
		systemServiceImpl.updateSystemAbout(pd);
		
		return InterfaceResult.returnSuccess(null);
	}
	
	
	/**
	 * 新增关于我们
	 * @author ZhouXiaobing 
	 * @date 2018年3月28日 上午11:08:39 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/addSystemAbout.do")
	public Object addSystemAbout() throws Exception {
		PageData pd = this.getPageData();
		
		systemServiceImpl.addSystemAbout(pd);
		
		return InterfaceResult.returnSuccess(null);
	}


	/**
	 * 根据id删除关于我们信息
	 * @author ZhouXiaobing 
	 * @date 2018年3月28日 上午11:14:29 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteAbout.do")
	public Object deleteAbout(@RequestParam(required = true) Integer id) throws Exception {
		
		systemServiceImpl.deleteAbout(id);
		
		return InterfaceResult.returnSuccess(null);
	}
}
