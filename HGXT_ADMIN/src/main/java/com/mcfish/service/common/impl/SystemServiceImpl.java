package com.mcfish.service.common.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mcfish.controller.base.InterfaceResult;
import com.mcfish.dao.DaoSupport;
import com.mcfish.entity.common.About;
import com.mcfish.entity.common.Charge;
import com.mcfish.entity.common.System;
import com.mcfish.entity.common.TbSystemError;
import com.mcfish.entity.common.Version;
import com.mcfish.service.common.ISystemService;
import com.mcfish.util.PageData;

/**
 * 系统service
 * @author ZhouXiaobing
 * @date 2018年3月24日 上午8:31:21 
 * @version 1.0
 */
@Service ("systemServiceImpl")
public class SystemServiceImpl implements ISystemService{
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	//添加一条系统异常记录
	@Override
	public void addTbSystemError(TbSystemError te) throws Exception {
		dao.update("SystemMapper.addTbSystemError", te);
	}
	

	//获取配置列表数据
	@SuppressWarnings("unchecked")
	@Override
	public List<System> selectAllList(PageData pd) throws Exception {
		
		pd.put("start", Integer.parseInt(pd.get("start").toString()));
		pd.put("length", Integer.parseInt(pd.get("length").toString()));
		pd.put("keyword", pd.get("search[value]").toString());
		
		return (List<System>) dao.findForList("SystemMapper.selectAllList", pd);
	}


	//根据ID查配置信息
	@Override
	public System selectSystemConfigByID(int id) throws Exception {
		return (System) dao.findForObject("SystemMapper.selectSystemConfigByID", id);
	}
	
	
	//修改基本配置信息
	@Override
	public void updateSystemConfigByID(PageData pd) throws Exception {
		dao.update("SystemMapper.updateSystemConfigByID", pd);
	}
	
	
	//添加一条系统配置记录
	@Override
	public Object insertSystemConfig(PageData pd) throws Exception {
		
		System config = this.getConfigByKey(pd.get("key").toString());
		if (config != null) {
			return InterfaceResult.returnFailureWithMsg("此关键词的KEY已存在");
		} else {
			dao.save("SystemMapper.insertSystemConfig", pd);
		}
		return InterfaceResult.returnSuccess(null);
	}
	
	
	//删除一条基础配置
	@Override
	public void deleteSystemConfigByID(int id) throws Exception {
		dao.delete("SystemMapper.deleteSystemConfigByID", id);
	}
	
	
	//根据key获取系统配置
	@Override
	public System getConfigByKey(String key) throws Exception {
		return (System) dao.findForObject("SystemMapper.getConfigByKey", key);
	}
	
	
	//查询所有充值配置
	@SuppressWarnings("unchecked")
	@Override
	public List<Charge> selectAllCharge(PageData pd) throws Exception {
		
		pd.put("start", Integer.parseInt(pd.get("start").toString()));
		pd.put("length", Integer.parseInt(pd.get("length").toString()));
		
		return (List<Charge>) dao.findForList("SystemMapper.selectAllCharge", pd);
	}
	
	
	//根据充值id查询充值配置
	@Override
	public Charge getChargeById(int id) throws Exception {
		return (Charge) dao.findForObject("SystemMapper.getChargeById", id);
	}
	
	
	//更新充值配置
	@Override
	public void updateSystemCharge(PageData pd) throws Exception {
		dao.update("SystemMapper.updateSystemCharge", pd);
	}
	
	
	//获取所有app版本信息列表
	@SuppressWarnings("unchecked")
	@Override
	public List<Version> selectAllApp(PageData pd) throws Exception {
		
		pd.put("start", Integer.parseInt(pd.get("start").toString()));
		pd.put("length", Integer.parseInt(pd.get("length").toString()));
		
		return (List<Version>) dao.findForList("SystemMapper.selectAllApp", pd);
	}
	
	
	//修改app版本调试开关
	@Override
	public void updateDebug(PageData pd) throws Exception {
		dao.update("SystemMapper.updateDebug", pd);
	}
	
	
	//添加App版本
	@Override
	public void addAppVersion(PageData pd) throws Exception {
		dao.save("SystemMapper.addAppVersion", pd);
	}
	

	//通过id获取版本信息
	@Override
	public Version getAppVersion(PageData pd) throws Exception {
		return (Version) dao.findForObject("SystemMapper.getAppVersion", pd);
	}

	
	//固件升级
	@Override
	public void updateAppVersion(PageData pd) {
		// TODO 固件升级
		
	}
	
	
	//获取所有关于我们信息列表
	@SuppressWarnings("unchecked")
	@Override
	public List<About> getAllAbout(PageData pd) throws Exception {
		
		pd.put("start", Integer.parseInt(pd.get("start").toString()));
		pd.put("length", Integer.parseInt(pd.get("length").toString()));
		pd.put("keyword", pd.get("search[value]").toString());
		
		return (List<About>) dao.findForList("SystemMapper.getAllAbout", pd);
	}
	
	
	//根据id查询关于我们信息
	@Override
	public About getAboutById(int id) throws Exception {
		return (About) dao.findForObject("SystemMapper.getAboutById", id);
	}
	
	
	//根据id更改关于我们信息
	@Override
	public void updateSystemAbout(PageData pd) throws Exception {
		dao.update("SystemMapper.updateSystemAbout", pd);
	}
	
	
	//新增关于我们
	@Override
	public void addSystemAbout(PageData pd) throws Exception {
		dao.save("SystemMapper.addSystemAbout", pd);
		
	}
	
	
	//删除关于我们
	@Override
	public void deleteAbout(int id) throws Exception {
		dao.delete("SystemMapper.deleteAbout", id);
	}
}
