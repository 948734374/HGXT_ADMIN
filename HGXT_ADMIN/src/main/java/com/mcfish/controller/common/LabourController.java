package com.mcfish.controller.common;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mcfish.controller.base.InterfaceResult;
import com.mcfish.entity.common.Labour;
import com.mcfish.service.common.IBasicService;
import com.mcfish.service.common.ILabourService;
import com.mcfish.util.PageData;


/**
 * 劳务公司管理
 * @author ZhangYichi
 * @data 2018年4月28日 下午3:56:17
 */
@Controller
@RequestMapping(value = "shareLabourController")
public class LabourController extends BasicController{

	@Resource(name = "LabourServiceImpl")
    private ILabourService LabourServiceImpl;	
	
	@Resource(name = "basicServiceImpl")
	private IBasicService basicServiceImpl;
	
	
	/**
	 * 访问劳务公司页面
	 * @author ZhangYichi
	 * @date 2018年4月28日 下午3:58:20
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/LabourPage.do")
	public ModelAndView toSystemPage() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		
		mv.addObject(pd);
		mv.setViewName("common/labour/labour");
		
		return mv;
	}

	
	/**
	 * 获取劳务公司数据
	 * @author ZhangYichi
	 * @data 2018年4月28日 下午4:03:29
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/getLabour.do")
	public Object getLabour() throws Exception{
	    PageData pd = this.getPageData();
		
		List<Labour> labourList = LabourServiceImpl.selectLabourList(pd);
		Long recordsTotal = labourList.size() == 0 ? 0l:labourList.get(0).getTotal();
		
		return InterfaceResult.returnTableSuccess(labourList, recordsTotal, pd.get("draw"));
	}
}
