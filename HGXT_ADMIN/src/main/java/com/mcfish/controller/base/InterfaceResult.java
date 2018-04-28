package com.mcfish.controller.base;


import java.util.HashMap;
import java.util.Map;

import com.mcfish.util.Logger;


/**
 * 接口返回信息
 * @author ZhouXiaobing
 * @date 2018年3月24日 上午8:36:41 
 * @version 1.0
 */
public class InterfaceResult {
	
	protected  Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 接口返回状态码
	 */
	public static final int SUCCESS = 0 ;//成功
	
	public static final int FAILURE = 1 ;//失败
	
	
	/**
	 * 接口返回提示信息
	 */
	public static final String Msg_success = "操作成功";
	
	public static final String Msg_failure = "操作失败"; //其实就是系统异常
	
	
	/**
	 * 返回成功结果
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 上午8:36:59 
	 * @param data
	 * @return
	 */
	public static Map<String, Object> returnSuccess(Object data){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", SUCCESS);
		map.put("resmsg", Msg_success);
		map.put("data", data);
		return map;
	}
	
	/**
	 * dataTable的成功结果返回
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 上午8:37:07 
	 * @param data
	 * @param total: dataTable分页需要的总条数
	 * @param draw： dataTable需要的调用次数
	 * @return
	 */
	public static Map<String, Object> returnTableSuccess(Object data, Long total, Object draw){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", SUCCESS);
		map.put("resmsg", Msg_success);
		map.put("data", data);
		map.put("recordsTotal", total);   //dataTable分页需要
		map.put("recordsFiltered", total);//dataTable分页需要
		map.put("draw",draw);//dataTable分页需要
		return map;
	}
	
	
	/**
	 * 返回成功结果  和  自定义成功信息
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 上午8:37:53 
	 * @param data
	 * @param msg
	 * @return
	 */
	public static Map<String, Object> returnSuccessWithMsgAndData(Map<String, Object> data , String msg){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", SUCCESS);
		map.put("resmsg", msg);
		map.put("data", data);
		return map;
	}

	/**
	 * 返回失败结果
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 上午8:38:24 
	 * @return
	 */
	public static Map<String, Object> returnFailure(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", FAILURE);
		map.put("resmsg", Msg_failure);
		map.put("data", new HashMap<String, Object>());
		new InterfaceResult().logger.error("++++++++++ 调用接口时系统异常  ++++++++++");
		return map;
	}
	
	
	/**
	 * 返回失败结果   提示信息自己定义
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 上午8:38:31 
	 * @param msg
	 * @return
	 */
	public static Map<String, Object> returnFailureWithMsg(String msg){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", FAILURE);
		map.put("resmsg", msg);
		map.put("data", new HashMap<String, Object>());
		new InterfaceResult().logger.error("++++++++++ 返回失败信息 :" + msg +"  ++++++++++");
		return map;
	}
	
	
	
	/**
	 * 返回token过期结果
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 上午8:38:39 
	 * @return
	 */
	public static Map<String, Object> returnExpire(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", SystemError.TOKEN_OVER.getCode());
		map.put("resmsg", SystemError.TOKEN_OVER.getMessage());
		map.put("data", new HashMap<String, Object>());
		new InterfaceResult().logger.warn("++++++++++ 接口调用时token过期  ++++++++++");
		new InterfaceResult().logger.warn("++++++++++   end   ++++++++++");
		return map;
	}
	
	
	/**
	 * 返回结果自定义
	 * @author ZhouXiaobing 
	 * @date 2018年3月24日 上午8:38:53 
	 * @param status
	 * @param Msg
	 * @param data
	 * @return
	 */
	public static Map<String, Object> returnResult(Integer status , String Msg , Map<String, Object> data){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("resmsg", Msg);
		map.put("data", data);
		return map;
	}
	
	/**
	 * 返回系统异常信息
	 * @author ZhouXiaobing 
	 * @param message 
	 * @param code 
	 * @date 2018年3月23日 下午7:59:53 
	 * @return
	 */
	public static Object returnError(int code, String message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", code);
		map.put("resmsg", message);
		map.put("data", new HashMap<String, Object>());
		new InterfaceResult().logger.error("++++++++++ 调用接口时系统异常  ++++++++++");
		return map;
	}
	
	
}
