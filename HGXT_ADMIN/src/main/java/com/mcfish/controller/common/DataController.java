package com.mcfish.controller.common;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.mcfish.service.common.IDataService;
import com.mcfish.service.common.IUserService;
import com.mcfish.util.PageData;


@Controller
@RequestMapping(value = "/shareDataController")
public class DataController extends BasicController {
	
	@Resource(name = "dataServiceImpl")
	private  IDataService dataServiceImpl;
	
	@Resource(name = "userServiceImpl")
	private IUserService userServiceImpl;
	
	
	/**
	 * 跳转到数据统计页面
	 * @author ZhouXiaobing 
	 * @date 2018年3月29日 下午1:47:39 
	 * @return
	 */
	@RequestMapping(value="/DataPage.do")
	public ModelAndView toDataPage(){ 
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		
		mv.setViewName("common/data/data");
		//加入定位锚点
		mv.addObject("anchor",pd.get("anchor"));
		mv.addObject("pd",pd);
		
		return mv;
	}
	
}
