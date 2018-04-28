package com.mcfish.service.common;

import java.util.List;

import com.mcfish.entity.common.About;
import com.mcfish.entity.common.Charge;
import com.mcfish.entity.common.System;
import com.mcfish.entity.common.TbSystemError;
import com.mcfish.entity.common.Version;
import com.mcfish.util.PageData;

/**
 * 系统service
 * @author ZhouXiaobing
 * @date 2018年3月24日 上午8:30:54 
 * @version 1.0
 */
public interface ISystemService {
	
	
	/**
	 * 添加一条系统异常记录
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午5:34:22 
	 * @param te
	 * @throws Exception
	 */
	void addTbSystemError(TbSystemError te) throws Exception;
	

	/**
	 * 获取配置列表数据
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午5:32:16 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	List<System> selectAllList(PageData pd) throws Exception;

	
	/**
	 * 根据ID查配置信息
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午5:49:37 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	System selectSystemConfigByID(int id) throws Exception;
	
	
	/**
	 * 修改基本配置信息
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午5:58:06 
	 * @param pd
	 * @throws Exception
	 */
	void updateSystemConfigByID(PageData pd) throws Exception;
	
	
	/**
	 * 添加一条系统配置记录
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午6:32:06 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	Object insertSystemConfig(PageData pd) throws Exception;
	
	
	/**
	 * 删除一条基础配置
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午8:16:49 
	 * @param id
	 * @throws Exception
	 */
	void deleteSystemConfigByID(int id) throws Exception;
	
	
	/**
	 * 根据key获取系统配置
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午6:59:31 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	System getConfigByKey(String key) throws Exception;
	
	
	/**
	 * 查询所有充值配置
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午7:58:41 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	List<Charge> selectAllCharge(PageData pd) throws Exception;
	
	
	/**
	 * 根据充值id查询充值配置
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午8:06:19 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Charge getChargeById(int id) throws Exception;
	
	
	/**
	 * 更新充值配置
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午8:10:20 
	 * @param pd
	 * @throws Exception
	 */
	void updateSystemCharge(PageData pd) throws Exception;
	
	
	/**
	 * 获取所有app版本信息列表
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午8:20:59 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	List<Version> selectAllApp(PageData pd) throws Exception;
	
	
	/**
	 * 修改app版本调试开关
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午8:33:13 
	 * @param pd
	 * @throws Exception
	 */
	void updateDebug(PageData pd) throws Exception;
	
	
	/**
	 * 添加App版本
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午9:02:36 
	 * @param pd
	 * @throws Exception
	 */
	void addAppVersion(PageData pd) throws Exception;
	
	
	/**
	 * 通过id获取版本信息
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午9:07:32 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	Version getAppVersion(PageData pd)throws Exception;
	
	
	/**
	 * 固件升级
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午9:10:50 
	 * @param pd
	 */
	void updateAppVersion(PageData pd);
	
	
	/**
	 * 获取所有关于我们信息列表
	 * @author ZhouXiaobing 
	 * @date 2018年3月27日 下午9:20:41 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	List<About> getAllAbout(PageData pd)throws Exception;
	
	
	/**
	 * 根据id查询关于我们信息
	 * @author ZhouXiaobing 
	 * @date 2018年3月28日 上午11:04:33 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	About getAboutById(int id)throws Exception;
	

	/**
	 * 根据id更改关于我们信息
	 * @author ZhouXiaobing 
	 * @date 2018年3月28日 上午11:09:37 
	 * @param pd
	 * @throws Exception
	 */
	void updateSystemAbout(PageData pd) throws Exception;
	
	
	/**
	 * 新增关于我们
	 * @author ZhouXiaobing 
	 * @date 2018年3月28日 上午11:09:30 
	 * @param pd
	 * @throws Exception
	 */
	void addSystemAbout(PageData pd) throws Exception;

	
	/**
	 * 删除关于我们
	 * @author ZhouXiaobing 
	 * @date 2018年3月28日 上午11:13:30 
	 * @param id
	 * @throws Exception
	 */
	void deleteAbout(int id) throws Exception;
	
}
