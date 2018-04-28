package com.mcfish.service.common.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mcfish.dao.DaoSupport;
import com.mcfish.entity.common.Banner;
import com.mcfish.entity.common.Guide;
import com.mcfish.entity.common.Version;
import com.mcfish.service.common.IOperService;
import com.mcfish.util.PageData;

/**
 * @author ZhouXiaobing
 * @date 2018年3月29日 下午3:14:37 
 * @version 1.0
 */
@Service ("operServiceImpl")
public class OperServiceImpl implements IOperService{
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;

	
	//获取banner列表数据
	@SuppressWarnings("unchecked")
	@Override
	public List<Banner> getAllBanners(PageData pd) throws Exception {
		
		pd.put("start", Integer.parseInt(pd.get("start").toString()));
		pd.put("length", Integer.parseInt(pd.get("length").toString()));
		pd.put("keyword", pd.get("search[value]").toString());
		
		return (List<Banner>) dao.findForList("OperMapper.getAllBanners", pd);
	}


	//添加banner
	@Override
	public void addBanner(PageData pd) throws Exception {
		dao.save("OperMapper.addBanner", pd);
	}


	//根据id获取banner详情
	@Override
	public Banner getBanner(PageData pd) throws Exception {
		return (Banner) dao.findForObject("OperMapper.getBanner", pd);
	}


	//更新banner
	@Override
	public void updateBanner(PageData pd) throws Exception {
		dao.update("OperMapper.updateBanner", pd);
	}


	//根据id删除banner
	@Override
	public void deleteBanner(PageData pd) throws Exception {
		dao.delete("OperMapper.deleteBanner", pd);
	}


	
	//获取所有引导页列表
	@SuppressWarnings("unchecked")
	@Override
	public List<Guide> getAllGuide(PageData pd) throws Exception {
		return (List<Guide>) dao.findForList("OperMapper.getAllGuide", pd);
	}


	//更新引导页
	@Override
	public void updateGuide(PageData pd) throws Exception {
		dao.update("OperMapper.updateGuide", pd);
	}


	//获取引导详情
	@Override
	public Guide getGuide(PageData pd) throws Exception {
		return (Guide) dao.findForObject("OperMapper.getGuide", pd);
	}


	//删除引导页
	@Override
	public void deleteGuide(PageData pd) throws Exception {
		dao.delete("OperMapper.deleteGuide", pd);
	}


	//获取所有app版本信息列表
	@SuppressWarnings("unchecked")
	@Override
	public List<Version> selectAllApp(PageData pd) throws Exception {
		
		pd.put("start", Integer.parseInt(pd.get("start").toString()));
		pd.put("length", Integer.parseInt(pd.get("length").toString()));
		
		return (List<Version>) dao.findForList("OperMapper.selectAllApp", pd);
	}


	//修改app版本信息
	@Override
	public void updateAppVersion(PageData pd) throws Exception {
		dao.update("OperMapper.updateAppVersion", pd);
	}


	//添加App版本
	@Override
	public void addAppVersion(PageData pd) throws Exception {
		dao.save("OperMapper.addAppVersion", pd);
	}


	//获取版本详情
	@Override
	public Version getAppVersion(PageData pd) throws Exception {
		return (Version) dao.findForObject("OperMapper.getAppVersion", pd);
	}

}
