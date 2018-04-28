package com.mcfish.service.common;

import java.util.List;

import com.mcfish.entity.common.Message;
import com.mcfish.util.PageData;

/**
 * @author ZhouXiaobing
 * @date 2018年3月28日 下午4:44:22 
 * @version 1.0
 */
public interface IMessageService {
	
	
	/**
	 * 获取所有消息
	 * @author ZhouXiaobing 
	 * @date 2018年3月28日 下午4:42:28 
	 * @param pageData
	 * @return
	 * @throws Exception
	 */
	List<Message> getAllMessage(PageData pageData) throws Exception;
	
	
	/**
	 * 删除一条消息推送
	 * @author ZhouXiaobing 
	 * @date 2018年3月28日 下午5:09:10 
	 * @param pageData
	 * @throws Exception
	 */
	void deleteMessage(PageData pageData) throws Exception;

	
	/**
	 * 新增推送消息
	 * @author ZhouXiaobing 
	 * @date 2018年3月28日 下午5:18:38 
	 * @param message
	 * @throws Exception
	 */
	void addMessage(PageData pd) throws Exception;
	

	/**
	 * 编辑消息推送
	 * @author ZhouXiaobing 
	 * @date 2018年3月28日 下午5:28:39 
	 * @param pd
	 * @throws Exception 
	 */
	void updatMessage(PageData pd) throws Exception;


	/**
	 * 获取消息详情
	 * @author ZhouXiaobing 
	 * @date 2018年3月28日 下午5:31:18 
	 * @param pd
	 * @return 
	 * @throws Exception 
	 */
	Message getMessage(PageData pd) throws Exception;
}
