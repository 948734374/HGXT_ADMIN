package com.mcfish.controller.common;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcfish.controller.base.BaseController;
import com.mcfish.controller.base.InterfaceResult;
import com.mcfish.entity.common.Feedback;
import com.mcfish.service.common.IFeedbackService;
import com.mcfish.util.PageData;

/**
 * 意见反馈
 * @author ZhouXiaobing
 * @date 2018年3月26日 下午5:38:20 
 * @version 1.0
 */
@Controller  
@RequestMapping(value = "/shareFeedbackController")
public class FeedbackController extends BaseController{
	
	@Resource(name = "feedbackServiceImpl")
	private IFeedbackService feedbackServiceImpl;
	
	
	/**
	 * 跳转到意见反馈页面
	 * @author ZhouXiaobing 
	 * @date 2018年3月26日 下午5:38:34 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/FeedbackPage.do")
	public ModelAndView toFeedbackPage()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		
		mv.setViewName("common/feedback/feedback");
		mv.addObject("pd",pd);
		
		return mv;
	}
	
	
	/**
	 * 获取所有意见反馈信息
	 * @author ZhouXiaobing 
	 * @date 2018年3月26日 下午5:39:09 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getAllFeedbackList.do")
	public Object getAllFeedbackList(HttpServletRequest request) throws Exception {
		PageData pd = new PageData(request);
		
		List<Feedback> feedbackList = feedbackServiceImpl.getAllFeedbackInfo(pd);
		Long feedbackTotal = feedbackList.size() == 0 ? 0l:(long) feedbackList.get(0).getTotal();
		
		return InterfaceResult.returnTableSuccess(feedbackList, feedbackTotal, pd.get("draw"));
	}
	
	
	/**
	 * 修改意见状态
	 * @author ZhouXiaobing 
	 * @date 2018年3月26日 下午5:40:06 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/updateFeedStatus.do")
	public Object updateFeedStatus(HttpServletRequest request) throws Exception {
		PageData pd = new PageData(request);
		
		feedbackServiceImpl.updateFeedStatus(pd);
		
		return InterfaceResult.returnSuccess(null);
	}
	
	
	/**
	 * 删除意见
	 * @author ZhouXiaobing 
	 * @date 2018年3月26日 下午5:40:22 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/deleteFeed.do")
	public Object deleteFeed(HttpServletRequest request) throws Exception {
		PageData pd = new PageData(request);
		
		feedbackServiceImpl.deleteFeed(pd);
		
		return InterfaceResult.returnSuccess(null);
	}

}
