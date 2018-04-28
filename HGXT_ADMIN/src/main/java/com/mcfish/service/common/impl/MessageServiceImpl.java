package com.mcfish.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mcfish.dao.DaoSupport;
import com.mcfish.entity.common.Message;
import com.mcfish.service.common.IMessageService;
import com.mcfish.util.PageData;

/**
 * @author ZhouXiaobing
 * @date 2018年3月28日 下午4:44:13 
 * @version 1.0
 */
@Service("messageServiceImpl")
public class MessageServiceImpl implements IMessageService{
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	//获取所有消息
	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getAllMessage(PageData pd) throws Exception {
		
		pd.put("start", Integer.parseInt(pd.get("start").toString()));
		pd.put("length", Integer.parseInt(pd.get("length").toString()));
		pd.put("keyword", pd.get("search[value]").toString());
		
		return (List<Message>) dao.findForList("MessageMapper.getAllMessage", pd);
	}

	
	//删除一条消息推送
	@Override
	public void deleteMessage(PageData pageData) throws Exception {
		dao.delete("MessageMapper.deleteMessage", pageData);
	}


	//新增推送消息
	@Override
	public void addMessage(PageData pd) throws Exception {
		dao.save("MessageMapper.addMessage", pd);
	}

	
	//编辑消息推送
	@Override
	public void updatMessage(PageData pd) throws Exception {
		dao.update("MessageMapper.updatMessage", pd);
	}


	//获取消息详情
	@Override
	public Message getMessage(PageData pd) throws Exception {
		return (Message) dao.findForObject("MessageMapper.getMessageById", pd);
	}
	
	
	//推送消息
	public void addUserMessage() throws Exception {
		PageData pd = new PageData();
		
		//TODO 测试
		List<String> list = new ArrayList<String>();
		
		list.add("1");
		
		String[] uids = (String[]) list.toArray();
		
		pd.put("uids", uids);
		
		dao.save("MessageMapper.addUserMessage", pd);
	}
	
}
