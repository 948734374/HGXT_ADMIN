package com.mcfish.service.common;

import java.util.List;

import com.mcfish.entity.common.Labour;
import com.mcfish.util.PageData;

public interface ILabourService {

	/**
	 * 获取劳务公司数据列表
	 * @author ZhangYichi 
	 * @date 2018年4月28日 下午4:25:43 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<Labour> selectLabourList(PageData pd) throws Exception;

}
