package com.mcfish.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mcfish.controller.base.InterfaceResult;
import com.mcfish.controller.base.MyException;
import com.mcfish.controller.base.SystemError;
import com.mcfish.entity.common.TbSystemError;
import com.mcfish.service.common.IBasicService;
import com.mcfish.service.common.ISystemService;
import com.mcfish.util.PublicUtil;


@Component
@Aspect
public class LogAspect {
	
	/******log record******/
	protected Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private HttpServletRequest req;
	
	@Resource(name = "systemServiceImpl")
	protected ISystemService systemService;
	
	@Resource(name = "basicServiceImpl")
	private IBasicService basicServiceImpl;
	
	/**
	 * 接口性能计算
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 上午7:53:38 
	 * @param jp
	 * @return
	 * @throws Throwable
	 */
	@SuppressWarnings("unused")
	@Around("execution(* com.mcfish.controller.*.*.*(..))") 
	public Object TimeLong(ProceedingJoinPoint jp) throws Throwable{
		
		//获取类名
		String className = jp.getThis().toString();  
		//获取方法名
	    String methodName = jp.getSignature().getName();
	    int i = 0;
        try {
    	    //方法开始时间
    	    long start = System.currentTimeMillis();
    	    Object result = null;
    	    try {
    	    	 result = jp.proceed();
    	    	//存一条系统操作记录日志
				//basicServiceImpl.addOperateLog(req, methodName, className);
			} catch (Exception e) {				
				
				log.error("\r\n类名:" + className + " ,\r\n" +
				          "请求IP地址:" + PublicUtil.getIpAddr(req) + " ,\r\n"+
	  	    		      "方法名:" + methodName + " ,\r\n"+
				          "异常连接--》",e);
				
				//异常处理
				if(e instanceof NullPointerException){
					//空指针异常处理
					TbSystemError te = new TbSystemError();
					te.setUser_id(Integer.parseInt(req.getSession().getAttribute("userId").toString()));
					te.setReq_ip(PublicUtil.getIpAddr(req));
					te.setMethod(className+"/"+methodName);
					te.setError(e.getMessage());
					te.setType((byte) 0);
					//添加一条系统异常记录
					systemService.addTbSystemError(te);
					i=1;
					return InterfaceResult.returnError(SystemError.NULL_EXCEPTION.getCode(),SystemError.NULL_EXCEPTION.getMessage());
				}else if(e instanceof MyException){
					//自定义异常
					return InterfaceResult.returnError(((MyException) e).getCode(),((MyException) e).getMessage());
				}else{
					//其他系统异常
					TbSystemError te = new TbSystemError();
					te.setUser_id(Integer.parseInt(req.getSession().getAttribute("userId").toString()));
					te.setReq_ip(PublicUtil.getIpAddr(req));
					te.setMethod(className+"/"+methodName);
					te.setError(e.getMessage());
					te.setType((byte) 0);
					//添加一条系统异常记录
					systemService.addTbSystemError(te);
					i=1;
					return InterfaceResult.returnError(SystemError.OTHER_EXCEPTION.getCode(), SystemError.OTHER_EXCEPTION.getMessage());
				}											
		    }
   	    
    	    log.info("\r\n类名:" + className + " ,\r\n" +
    	    		  "方法名:" + methodName + " ,\r\n" +
    	    		  "请求IP地址:" + PublicUtil.getIpAddr(req) + " ,\r\n" +
    	    		  "共计消耗:" + (System.currentTimeMillis() - start) + " ms \r\n");
   
    	   return result;
		} catch (Exception e) {
			log.error("接口性能计算异常--》",e);
			TbSystemError te = new TbSystemError();
			te.setUser_id(Integer.parseInt(req.getSession().getAttribute("userId").toString()));
			te.setReq_ip(PublicUtil.getIpAddr(req));
			te.setMethod(className+"/"+methodName);
			te.setError(e.getMessage());
			te.setType((byte) 0);
			systemService.addTbSystemError(te);
			i=1;
			return InterfaceResult.returnError(SystemError.OTHER_EXCEPTION.getCode(), SystemError.OTHER_EXCEPTION.getMessage());
		}finally {
		}
	}
}
