package com.mcfish.service.common.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mcfish.dao.DaoSupport;
import com.mcfish.entity.common.Home;
import com.mcfish.service.common.IHomeService;
import com.mcfish.util.PageData;

/**
 * @author TangLin
 * @date 2017年7月11日 下午2:13:36 
 * @version 1.0
 */
@Service("homeServiceImpl")
public class HomeServiceImpl implements IHomeService{
	
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	//获取平台头部数据信息 
	@Override
	public Home getHomeData() throws Exception {
		return (Home) dao.findForObject("HomeMapper.getHomeData",null);
	}


	//本周每日用户数量
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getEverydayUser(PageData pd) throws Exception {
		
		//获取当前时间
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		
		String year = sf.format(date).substring(0,4);
		String month = sf.format(date).substring(5,7);
		
		pd.put("year", year);
		pd.put("month", month);
		
		return (List<Map<String, Object>>) dao.findForList("HomeMapper.getEverydayUser",pd);
	}


	//本周每日订单数量
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getEverydayOrder(PageData pd) throws Exception {
		
		//获取当前时间
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		
		String year = sf.format(date).substring(0,4);
		String month = sf.format(date).substring(5,7);
		
		pd.put("year", year);
		pd.put("month", month);
				
		return  (List<Map<String, Object>>) dao.findForList("HomeMapper.getEverydayOrder",pd);
	}
}
