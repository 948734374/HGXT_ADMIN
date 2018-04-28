package com.mcfish.resolver;

/*import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.mcfish.entity.common.TbSystemError;
import com.mcfish.service.common.ISystemService;
import com.mcfish.util.Logger;
import com.mcfish.util.PublicUtil;*/

/**
 * 全局处理异常捕获
 * @author ZhouXiaobing
 * @date 2018年3月24日 上午9:08:09
 * @version 1.0
 */
/*@Component
public class MyExceptionResolver implements HandlerExceptionResolver {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "systemService")
	protected ISystemService systemService;

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		HandlerMethod method = (HandlerMethod) handler;
		String methodName = method.getMethod().getName();
		String className = method.getBean().toString();
		
		log.error("\r\n类名:" + className + " ,\r\n" + 
				  "请求IP地址:" + PublicUtil.getIpAddr(request) + " ,\r\n" + 
				  "方法名:" +  methodName + " ,\r\n" + "异常连接--》", ex);
		
		TbSystemError te = new TbSystemError();
		te.setUser_id(Integer.parseInt(request.getSession().getAttribute("userId").toString()));
		te.setReq_ip(PublicUtil.getIpAddr(request));
		te.setMethod(className+"/"+methodName);
		te.setError(ex.getMessage());
		te.setType((byte) 0);
		//添加一条系统异常记录
		try {
			systemService.addTbSystemError(te);
		} catch (Exception e) {
			log.error("系统异常日志存入错误",e);
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", ex.toString().replaceAll("\n", "<br/>"));
		mv.setViewName("error");
		return mv;
	}
}*/
