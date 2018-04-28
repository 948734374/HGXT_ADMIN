package com.mcfish.service.common.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mcfish.dao.DaoSupport;
import com.mcfish.entity.common.Labour;
import com.mcfish.service.common.ILabourService;
import com.mcfish.util.PageData;

@Service("LabourServiceImpl")
public class LabourServiceImpl implements ILabourService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	
	//获取劳务公司数据列表
	@SuppressWarnings("unchecked")
	@Override
	public List<Labour> selectLabourList(PageData pd) throws Exception{
		
		pd.put("start", Integer.parseInt(pd.get("start").toString()));
		pd.put("length", Integer.parseInt(pd.get("length").toString()));
		pd.put("keyword", pd.get("search[value]").toString());
		
		return (List<Labour>)dao.findForList("LabourMapper.getLabourList", pd);
	}
}
