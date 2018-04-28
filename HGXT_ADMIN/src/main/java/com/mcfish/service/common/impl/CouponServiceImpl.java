package com.mcfish.service.common.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mcfish.dao.DaoSupport;
import com.mcfish.entity.common.Coupon;
import com.mcfish.service.common.ICouponsService;
import com.mcfish.util.PageData;
import com.mcfish.util.PublicUtil;

/**
 * 平台优惠券
 * @author ZhouXiaobing
 * @date 2018年3月31日 上午11:00:59
 * @version 1.0
 */

@Service("couponServiceImpl")
public class CouponServiceImpl implements ICouponsService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	
	// 添加优惠券
	@Override
	public void addCoupon(PageData pd) throws Exception {
		
		pd.put("code", PublicUtil.getRandomChar());
		
		dao.save("couponMapper.addCoupon", pd);
	}

	
	// 获取所有的优惠券
	@SuppressWarnings("unchecked")
	@Override
	public List<Coupon> getAllCoupons(PageData pd) throws Exception {
		
		pd.put("start", Integer.parseInt(pd.get("start").toString()));
		pd.put("length", Integer.parseInt(pd.get("length").toString()));
		pd.put("keyword", pd.get("search[value]").toString());
		
		return (List<Coupon>) dao.findForList("couponMapper.getAllCoupons", pd);
	}

	
	//删除优惠券
	@Override
	public void deleteCoupon(PageData pd) throws Exception {
		dao.delete("couponMapper.deleteCoupon", pd);
	}

	
	//获取优惠券详情
	@Override
	public Coupon getCouponInfo(PageData pd) throws Exception {
		return (Coupon) dao.findForObject("couponMapper.getCouponInfo", pd);
	}

	// 更新优惠券
	@Override
	public void updateCoupon(PageData pd) throws Exception {
		dao.update("couponMapper.updateCoupon", pd);
	}
}
