package com.mcfish.service.common;

import java.util.List;

import com.mcfish.entity.common.Feedback;
import com.mcfish.util.PageData;

/**
 * @author TangLin
 * @date 2017年7月19日 上午9:55:47 
 * @version 1.0
 */
public interface IFeedbackService {
	
	/**
	 * 获取所有反馈信息
	 * @author ZhouXiaobing 
	 * @date 2018年3月26日 下午5:41:19 
	 * @param pageData
	 * @return
	 * @throws Exception
	 */
	List<Feedback> getAllFeedbackInfo(PageData pageData) throws Exception;
	
	
	/**
	 * 修改反馈意见状态
	 * @author ZhouXiaobing 
	 * @date 2018年3月26日 下午5:41:32 
	 * @param pageData
	 * @throws Exception
	 */
	void updateFeedStatus(PageData pageData) throws Exception;
	
	
	/**
	 * 删除反馈意见状态
	 * @author ZhouXiaobing 
	 * @date 2018年3月26日 下午5:41:42 
	 * @param pageData
	 * @throws Exception
	 */
	public void deleteFeed(PageData pageData) throws Exception;
	
	
}
