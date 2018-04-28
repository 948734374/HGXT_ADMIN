package com.mcfish.util;

/**
 * 基本配置信息类
 * @author ZhouXiaobing
 * @date 2018年4月19日 下午1:34:22
 * @version 1.0
 */
public class BasicInfo {
	
	//七牛账号信息
	public static final String  ACCESS_KEY = "";  // 账号的ACCESS_KEY
	public static final String SECRET_KEY = "";  //账号的SECRET_KEY
	public static final String SPACE = "";  //表空间
	
	
	/**支付宝账号信息配置**/
	// 商户appid
	public static String APPID = "";
	// 私钥 pkcs8格式的
	public static String RSA_PRIVATE_KEY = "";
	// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "";
	// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
	public static String return_url = "";
	// 请求网关地址https://openapi.alipay.com/gateway.do
	public static String URL = "https://openapi.alipay.com/gateway.do";
	// 编码
	public static String CHARSET = "GBK";
	// 返回格式
	public static String FORMAT = "json";
	// 支付宝公钥
	public static String ALIPAY_PUBLIC_KEY = "";
	// 日志记录目录
	public static String log_path = "/log";
	// RSA2
	public static String SIGNTYPE = "RSA2";
	
	
	/**微信账号信息配置**/
	
	//公众号appId
	public static final String appID = "";
	//公众号appSecret
	public static final String APP_AppSecret = "";
	//商户证书路径
	public static final String KeyPath = "";
	//微信退款接口
	public static final String httpurl ="";
	//商户号
	public static final String MchId = "";
	//商户号秘钥
	public static final String MchKey = "";
	//商家名
	public static final String Send_Name = "";
}
