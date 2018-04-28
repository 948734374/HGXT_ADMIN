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
import com.mcfish.entity.common.Message;
import com.mcfish.service.common.IMessageService;
import com.mcfish.util.PageData;

/**
 * 消息推送
 * @author ZhouXiaobing
 * @date 2018年3月28日 下午4:26:17 
 * @version 1.0
 */
@Controller   
@RequestMapping(value = "/shareMessageController")
public class MessageController extends BaseController{
	
	@Resource(name = "messageServiceImpl")
	private IMessageService messageServiceImpl;
	
	
	/**
	 * 跳转到推送消息展示页面
	 * @author ZhouXiaobing 
	 * @date 2018年3月28日 下午4:27:14 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/MessagePage.do")
	public ModelAndView toMessagePage()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		
		mv.setViewName("common/message/message");
		mv.addObject("pd",pd);
		
		return mv;
	}
	
	
	/**
	 * 获取所有消息
	 * @author ZhouXiaobing 
	 * @date 2018年3月28日 下午4:42:12 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getAllMessage.do")
	public Object getAllMessage(HttpServletRequest request) throws Exception {
		PageData pd = new PageData(request);
		
		List<Message> messageList = messageServiceImpl.getAllMessage(pd);
		Long messageTotal = messageList.size() == 0 ? 0l:messageList.get(0).getTotal();
		
		return InterfaceResult.returnTableSuccess(messageList, messageTotal, pd.get("draw"));
	}
	
	
	/**
	 * 删除一条消息推送
	 * @author ZhouXiaobing 
	 * @date 2018年3月28日 下午5:08:35 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteMessage.do")
	public Object deleteMessage() throws Exception{
		PageData pd = this.getPageData();
		
		messageServiceImpl.deleteMessage(pd);
		
		return InterfaceResult.returnSuccess(null);
	}
	
	
	/**
	 * 新增推送消息
	 * @author ZhouXiaobing 
	 * @date 2018年3月28日 下午5:16:56 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/addMessage.do")
	public Object addMessage() throws Exception{
		PageData pd = this.getPageData();
		
		messageServiceImpl.addMessage(pd);
		
		return InterfaceResult.returnSuccess(null);
	}
	
	
	/**
	 * 编辑消息推送
	 * @author ZhouXiaobing 
	 * @date 2018年3月28日 下午5:28:14 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/updatMessage.do")
	public Object updatMessage() throws Exception{
		PageData pd = this.getPageData();
		
		messageServiceImpl.updatMessage(pd);
		
		return InterfaceResult.returnSuccess(null);	
	}
	
	
	/**
	 * 获取消息详情
	 * @author ZhouXiaobing 
	 * @date 2018年3月28日 下午5:30:47 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getMessage.do")
	public Object getMessage() throws Exception{
		PageData pd = this.getPageData();
		
		Message message = messageServiceImpl.getMessage(pd);
		
		return InterfaceResult.returnSuccess(message);	
	}

}
