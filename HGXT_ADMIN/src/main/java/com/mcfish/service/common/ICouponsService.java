package com.mcfish.service.common;

import java.util.List;

import com.mcfish.entity.common.Coupon;
import com.mcfish.util.PageData;


/**
 * 平台优惠券
 * @author ZhouXiaobing
 * @date 2018年3月31日 上午11:00:10 
 * @version 1.0
 */
public interface ICouponsService {


	/**
	 * 添加优惠券
	 * @author ZhouXiaobing 
	 * @date 2018年3月31日 上午10:58:09 
	 * @param pd
	 * @throws Exception 
	 */
	void addCoupon(PageData pd) throws Exception;


	/**
	 * 获取所有的优惠券
	 * @author ZhouXiaobing 
	 * @date 2018年3月31日 上午10:58:39 
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	List<Coupon> getAllCoupons(PageData pd) throws Exception;
	

	/**
	 * 删除优惠券
	 * @author ZhouXiaobing 
	 * @date 2018年3月31日 上午10:58:53 
	 * @param pd
	 * @throws Exception 
	 */
	void deleteCoupon(PageData pd) throws Exception;
	
	
	/**
	 * 获取优惠券详情
	 * @author ZhouXiaobing 
	 * @date 2018年3月31日 上午10:59:15 
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	Coupon getCouponInfo(PageData pd) throws Exception;
	
	
	/**
	 * 更新优惠券
	 * @author ZhouXiaobing 
	 * @date 2018年3月31日 上午10:59:32 
	 * @param pd
	 * @throws Exception 
	 */
	 void updateCoupon(PageData pd) throws Exception;
}