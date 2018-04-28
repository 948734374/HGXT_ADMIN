package com.mcfish.controller.common;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.mcfish.controller.base.BaseController;
import com.mcfish.controller.base.InterfaceResult;
import com.mcfish.entity.common.Banner;
import com.mcfish.entity.common.Guide;
import com.mcfish.entity.common.Version;
import com.mcfish.service.common.IOperService;
import com.mcfish.util.PageData;


/**
 * 运营管理控制层
 * @author ZhouXiaobing
 * @date 2018年3月29日 下午2:42:01 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/shareOperController")
public class OperController extends BaseController {

	@Resource(name = "operServiceImpl")
	private IOperService operServiceImpl;

	
	/**
	 * 跳转到运营管理页面
	 * @author ZhouXiaobing 
	 * @date 2018年3月29日 下午2:42:43 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/OperPage.do")
	public ModelAndView toOperPage() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		
		mv.setViewName("common/oper/oper");
		mv.addObject("pd", pd);
		
		return mv;
	}

	
	/**
	 * 获取banner列表数据
	 * @author ZhouXiaobing 
	 * @date 2018年3月29日 下午3:07:45 
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/getAllBanner.do")
	public Object getAllBanner() throws Exception{
		PageData pd = this.getPageData();
		
		List<Banner> bannerList = operServiceImpl.getAllBanners(pd);
		Long total = bannerList.size() == 0 ? 0l:bannerList.get(0).getTotal();
		
		return InterfaceResult.returnTableSuccess(bannerList, total, pd.get("draw"));
	}
	
	
	/**
	 * 添加banner
	 * @author ZhouXiaobing 
	 * @date 2018年3月29日 下午3:09:03 
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/addBanner.do")
	public Object addBanner() throws Exception{
		PageData pd=this.getPageData();
		
		operServiceImpl.addBanner(pd);		
		
		return InterfaceResult.returnSuccess(null);
	}
	
	
	/**
	 * 根据id获取banner详情
	 * @author ZhouXiaobing 
	 * @date 2018年3月29日 下午3:10:33 
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/getBanner.do")
	public Object getBanner() throws Exception{
		PageData pd=this.getPageData();
		
		Banner banner = operServiceImpl.getBanner(pd);
		
		return InterfaceResult.returnSuccess(banner);
	}
	

	/**
	 * 更新banner
	 * @author ZhouXiaobing 
	 * @date 2018年3月29日 下午3:12:17 
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/updateBanner.do")
	public Object updateBanner() throws Exception{
		PageData pd = this.getPageData();
		
		operServiceImpl.updateBanner(pd);
		
		return InterfaceResult.returnSuccess(null);
	}

	
	/**
	 * 根据id删除banner
	 * @author ZhouXiaobing 
	 * @date 2018年3月29日 下午3:13:03 
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/deleteBanner.do")
	public Object deleteBanner() throws Exception{
	    PageData pd = this.getPageData();
		
		operServiceImpl.deleteBanner(pd);		
		
		return InterfaceResult.returnSuccess(null);
	}
	
	
	
	/**
	 * 获取所有引导页
	 * @author ZhouXiaobing 
	 * @date 2018年3月29日 下午3:49:48 
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/getAllGuide.do")
	public Object getAllGuide() throws Exception{
		PageData pd = this.getPageData();
		
		List<Guide> guides = operServiceImpl.getAllGuide(pd);
		//Long total =  guides.size() == 0 ? 0l:guides.get(0).getTotal();
		//return InterfaceResult.returnTableSuccess(guides, total, pd.get("draw"));	
		
		return InterfaceResult.returnSuccess(guides);
	}


	/**
	 * 更新引导页
	 * @author ZhouXiaobing 
	 * @date 2018年3月29日 下午3:56:22 
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/updateGuide.do")
	public Object updateGuide() throws Exception{
		PageData pd = this.getPageData();
		
		operServiceImpl.updateGuide(pd);
		
		return InterfaceResult.returnSuccess(null);			
	}
	
	
	/**
	 * 获取引导详情
	 * @author ZhouXiaobing 
	 * @date 2018年3月29日 下午3:58:00 
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/getGuide.do")
	public Object getGuide() throws Exception{
		PageData pd = this.getPageData();
		
        Guide guide = operServiceImpl.getGuide(pd);
        
		return InterfaceResult.returnSuccess(guide);		
	}
	
	
	/**
	 * 删除引导页
	 * @author ZhouXiaobing 
	 * @date 2018年3月30日 下午4:08:37 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/deleteGuide.do")
	public Object deleteGuide() throws Exception{
		PageData pd = this.getPageData();
		
		operServiceImpl.deleteGuide(pd);
		
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
		PageData pd = this.getPageData();
		
		List<Version> list = operServiceImpl.selectAllApp(pd);
		Long recordsTotal = list.size() == 0 ? 0l:list.get(0).getTotal();
		
		return InterfaceResult.returnTableSuccess(list, recordsTotal, pd.get("draw"));
	}
	
	
	/**
	 * 修改app版本信息
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午8:33:02 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/updateAppVersion.do")
	public Object updateAppVersion() throws Exception {
		PageData pd = this.getPageData();
		
		operServiceImpl.updateAppVersion(pd);
		
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
		
		operServiceImpl.addAppVersion(pd);
		
		return InterfaceResult.returnSuccess(null);
	}
	
	
	/**
	 * 获取版本详情
	 * @author ZhouXiaobing 
	 * @date 2018年3月31日 下午5:16:35 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getAppVersion.do")
	public Object getAppVersion() throws Exception {
		PageData pd = this.getPageData();
		
		Version version =  operServiceImpl.getAppVersion(pd);
		
		return InterfaceResult.returnSuccess(version);
	}
}
