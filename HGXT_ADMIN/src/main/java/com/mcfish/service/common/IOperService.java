package com.mcfish.service.common;

import java.util.List;
import com.mcfish.entity.common.Banner;
import com.mcfish.entity.common.Guide;
import com.mcfish.entity.common.Version;
import com.mcfish.util.PageData;


/**
 * 运营管理service
 * @author ZhouXiaobing
 * @date 2018年3月29日 下午3:13:56 
 * @version 1.0
 */
public interface IOperService {

	
	/**
	 * 获取banner列表数据
	 * @author ZhouXiaobing 
	 * @date 2018年3月29日 下午3:17:04 
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	List<Banner> getAllBanners(PageData pd) throws Exception;

	
	/**
	 * 添加banner
	 * @author ZhouXiaobing 
	 * @date 2018年3月29日 下午3:18:09 
	 * @param pd
	 * @throws Exception 
	 */
	void addBanner(PageData pd) throws Exception;


	/**
	 * 根据id获取banner详情
	 * @author ZhouXiaobing 
	 * @date 2018年3月29日 下午3:18:50 
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	Banner getBanner(PageData pd) throws Exception;


	/**
	 * 更新banner
	 * @author ZhouXiaobing 
	 * @date 2018年3月29日 下午3:19:48 
	 * @param pd
	 * @throws Exception 
	 */
	void updateBanner(PageData pd) throws Exception;


	/**
	 * 根据id删除banner
	 * @author ZhouXiaobing 
	 * @date 2018年3月29日 下午3:20:20 
	 * @param pd
	 * @throws Exception 
	 */
	void deleteBanner(PageData pd) throws Exception;


	/**
	 * 获取所有引导页列表
	 * @author ZhouXiaobing 
	 * @date 2018年3月29日 下午3:55:25 
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	List<Guide> getAllGuide(PageData pd) throws Exception;


	/**
	 * 更新引导页
	 * @author ZhouXiaobing 
	 * @date 2018年3月29日 下午3:57:11 
	 * @param pd
	 * @throws Exception 
	 */
	void updateGuide(PageData pd) throws Exception;


	/**
	 * 获取引导详情
	 * @author ZhouXiaobing 
	 * @date 2018年3月29日 下午3:59:56 
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	Guide getGuide(PageData pd) throws Exception;


	/**
	 * 删除引导页
	 * @author ZhouXiaobing 
	 * @date 2018年3月30日 下午4:09:11 
	 * @param pd
	 * @throws Exception 
	 */
	void deleteGuide(PageData pd) throws Exception;


	/**
	 * 获取所有app版本信息列表
	 * @author ZhouXiaobing 
	 * @date 2018年3月31日 下午5:10:42 
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	List<Version> selectAllApp(PageData pd) throws Exception;


	/**
	 * 修改app版本信息
	 * @author ZhouXiaobing 
	 * @date 2018年3月31日 下午5:11:05 
	 * @param pd
	 * @throws Exception 
	 */
	void updateAppVersion(PageData pd) throws Exception;


	/**
	 * 添加App版本
	 * @author ZhouXiaobing 
	 * @date 2018年3月31日 下午5:11:14 
	 * @param pd
	 * @throws Exception 
	 */
	void addAppVersion(PageData pd) throws Exception;


	/**
	 * 获取版本详情
	 * @author ZhouXiaobing 
	 * @date 2018年3月31日 下午5:16:57 
	 * @param pd
	 * @return 
	 * @throws Exception 
	 */
	Version getAppVersion(PageData pd) throws Exception;
	
}
