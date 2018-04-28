package com.mcfish.util.quanxian;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EditRequest {

 
	//平台首页模块
	public static List<String> homeEdit = new ArrayList<String>(Arrays.asList(
			"shareHomeController/test.do"  //测试权限，不作实际使用（不让homeEdit不为空）
			
	));
	
	
	//用户管理模块
	public static List<String> userEdit = new ArrayList<String>(Arrays.asList(
		"shareUserController/updateUserInfo.do",  //更新用户基本信息
		"shareUserController/updateUserStatus.do",    //更新用户状态
		"shareUserController/deleteUser.do",    //根据用户id删除
		"shareUserController/deleteBankCard.do",   // 根据银行卡id删除
		"shareUserController/deleteCoupon.do",    //删除优惠券
		"shareUserController/deleteDevCoupon.do",  //删除抵扣券
		"shareUserController/overOrderByOid.do"  //根据订单id结束订单
		
	));
	
	
	//地图分布模块
	public static List<String> mapDistrubitionEdit = new ArrayList<String>(Arrays.asList(
		"shareMapDistrubitionController/test.do"  //测试权限，不作实际使用（不让mapDistrubitionEdit不为空）
				
	));
	
	
	//机柜管理模块
	public static List<String> cabinetEdit = new ArrayList<String>(Arrays.asList(
		"shareCabinetController/deletCabinet.do",   //删除机柜
		"shareCabinetController/addCabinet.do",   //新增机柜
		"shareCabinetController/editCabinet.do",   //修改机柜
		"shareCabinetController/openDevice.do"   //开启仓位弹出充电宝
		
	));
	
	
	//设备管理模块
	public static List<String> deviceEdit = new ArrayList<String>(Arrays.asList(
		"shareDeviceController/deletDevice.do",   //删除设备
		"shareDeviceController/addDevice.do",   //新增设备
		"shareDeviceController/editRepairDevice.do"   //更改已故障设备在状态
	
	));
	
	
	//商家管理模块
	public static List<String> merchartEdit = new ArrayList<>(Arrays.asList(
		"shareMerchartManager/addMerchart.do",    //添加商家
		"shareMerchartManager/deletMerchart.do",   //根据id删除商家
		"shareMerchartManager/updateMerchart.do",   //根据商家Id更新商家
		"shareMerchartManager/addType.do",   //添加商家分类
		"shareMerchartManager/deleteType.do",  //根据id删除分类
		"shareMerchartManager/updateMerchartStatus.do",   //更新商家状态
		"shareMerchartManager/UpdateUser.do",   //更新商家店主
		"shareMerchartManager/updateProportion.do",   //更新商家分成
		
		"shareMerchantCommison/settlement.do",   //结算佣金给商家
		"shareMerchantCommison/toZhuanZhang.do"   //转账佣金给商家
			
	));
	
	
	//渠道管理模块
	public static List<String> agentEdit = new ArrayList<>(Arrays.asList(
		"shareAgentController/insertAgentInfos.do",    //添加渠道账户信息
		"shareAgentController/updateAgentById.do",  //保存编辑渠道用户信息
		"shareAgentController/deleteAgentById.do",  //删除
		"shareAgentController/freezeAgentAcount.do",   //冻结账户
		"shareAgentController/unfreezeAgentAcount.do",  //解冻账户  
		"shareAgentController/saveAgent.do",   //保存代理商信息
		"shareAgentController/updatePwd.do",  //更改代理商密码
		"shareAgentController/downloadPicBySno.do"  //生成二维码
		
	));
	 
	
	//优惠券管理模块
	public static List<String> couponEdit = new ArrayList<>(Arrays.asList(
		"shareCouponController/addCoupon.do",  //添加优惠券
		"shareCouponController/deletCoupons.do",   //删除优惠券
		"shareCouponController/updateCoupon.do",   //更新优惠券
		"shareCouponController/updateCouponsStatus.do",   //更新优惠券状态
		"shareCouponController/addDedCoupon.do",  //添加抵扣券
		"shareCouponController/updateDedCoupon.do"   //更新抵扣券
		
	));
	
	
	//消息推送管理模块
	public static List<String> pushMessageEdit = new ArrayList<>(Arrays.asList(
		"sharePushMessageController/deleteMessage.do",  //删除
		"sharePushMessageController/saveMessage.do"   //新增消息推送
		
	));
	
	
	//数据统计模块
	public static List<String> moneyStatisticalEdit = new ArrayList<>(Arrays.asList(
		"shareMoneyController/deleteMoney.do",  //删除财务记录 
		"shareMoneyController/deleteUse.do",   //根据id删除设备使用记录
		"shareMoneyController/updateWithdrawsStatus.do"   //更新提现状态
		
	));
	
	
	//意见反馈管理模块
	public static List<String> feedbackEdit = new ArrayList<>(Arrays.asList(
		"shareFeedbackController/changeFeedStatus.do",  //修改意见状态
		"shareFeedbackController/deleteFeed.do"  //删除意见状态
		
	));
	
	
	//管理员模块
	public static List<String> adminEdit = new ArrayList<>(Arrays.asList(
		"shareAdminConfigController/addAdmin.do",  //添加管理员
		"shareAdminConfigController/chongZhiMiMa.do",     //重置密码
		"shareAdminConfigController/delete.do",   //删除账户
		"shareAdminConfigController/updateStatus.do",  //启用/停用账号
		"shareAdminConfigController/updateById.do",  //修改用户名称/姓名
		"shareAdminConfigController/saveAuth.do"   //权限保存
			 
	));
	
	
	//系统设置管理模块
	public static List<String> systemConfigEdit = new ArrayList<>(Arrays.asList(
		"shareSystemConfigController/addSystemConfig.do",  //添加基础配置
		"shareSystemConfigController/saveSystemCharge.do",  //更新金额配置
		"shareSystemConfigController/deleteSystemConfigById.do",  //删除一条信息
		"shareSystemConfigController/changeDebug.do",     //修改app版本调试开关
		"shareSystemConfigController/deleteAbout.do",   //删除关于我们信息
		"shareSystemConfigController/UpdateSystemAbout.do",  //编辑关于我们
		"shareSystemConfigController/distributionSet.do",  //设置分成比例
		"shareSystemConfigController/addAppVersion.do",   //添加App版本
		"shareSystemConfigController/saveSystemConfig.do"  //编辑基本信息
	));
	
	
	//运营管理模块
	public static List<String> bannerEdit = new ArrayList<String>(Arrays.asList(
		"shareBannerController/addBanner.do",  //添加banner
		"shareBannerController/updateBannerById.do",    //更新banner
		"shareBannerController/deleteBanner.do",    //删除banner
		"shareBannerController/bannerXiaXian.do",   // 根据id更新banner的状态为下线
		"shareBannerController/bannerShangXian.do"    //根据id更新banner的状态为上线
				
	));
	 
 
	//小二管理模块
	public static List<String> smallTwoEdit = new ArrayList<>(Arrays.asList(
		"shareSmallTwoController/deletSmallTwo.do",    //根据id删除某个小二
		"shareSmallTwoController/updateSmallTwo.do"  //更新小二信息
	));

	
//添加每个模块到map中
	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	public final static Map map = new HashMap() {{    
		put("100",homeEdit);				//平台首页模块
		put("101",userEdit);				//用户管理模块
		put("102",mapDistrubitionEdit);		//地图分布模块
		put("103",cabinetEdit);				//机柜管理模块
		put("104",deviceEdit);				//设备管理模块
		put("105",merchartEdit);			//商家管理模块
		put("106",agentEdit);				//渠道管理模块
		put("107",couponEdit);				//优惠券管理模块
		put("108",pushMessageEdit);			//消息推送管理模块
		put("109",moneyStatisticalEdit);	//数据统计模块
		put("110",feedbackEdit);			//意见反馈管理模块
		put("111",adminEdit);				//管理员模块
		put("112",systemConfigEdit);		//系统设置管理模块
		put("113",bannerEdit);				//运营管理模块
		put("114",smallTwoEdit);			//小二管理模块
	   	    
	}}; 
}




