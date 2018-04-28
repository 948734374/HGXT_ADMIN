package com.mcfish.service.common.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mcfish.dao.DaoSupport;
import com.mcfish.entity.common.Feedback;
import com.mcfish.service.common.IFeedbackService;
import com.mcfish.util.PageData;

/**
 * 
 * @author ZhouXiaobing
 * @date 2018年3月26日 下午5:42:52 
 * @version 1.0
 */
@Service("feedbackServiceImpl")
public class FeedbackServiceImpl implements IFeedbackService{
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	

	//获取所有反馈信息
	@SuppressWarnings("unchecked")
	@Override
	public List<Feedback> getAllFeedbackInfo(PageData pd) throws Exception {
		
		pd.put("start", Integer.parseInt( pd.get("start").toString() ) );
		pd.put("length", Integer.parseInt( pd.get("length").toString() ) );
		pd.put("keyword", pd.get("search[value]").toString());
		
		return (List<Feedback>) dao.findForList("FeedbackMapper.getAllFeedbackInfo", pd);
	}

	
	//修改反馈意见状态
	@Override
	public void updateFeedStatus(PageData pageData) throws Exception {
		dao.update("FeedbackMapper.updateFeedStatus", pageData);
	}

	
	//删除反馈意见状态
	@Override
	public void deleteFeed(PageData pageData) throws Exception {
		dao.delete("FeedbackMapper.deleteFeed", pageData);
	}

}
