package com.mcfish.service.common.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mcfish.dao.DaoSupport;
import com.mcfish.service.common.IDataService;

@Service("dataServiceImpl")
public class DataServiceImpl  implements IDataService{
	@Resource(name = "daoSupport")
	private DaoSupport dao;

	
}
