package com.mcfish.util.alipay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeCreateRequest;
import com.mcfish.util.BasicInfo;

/**
 * 支付宝初始化参数
 * 
 * @author wuhong  2017年8月14日 下午12:54:30 
 * @version fengxiang 1.0
 */
public class AlipayInit {

	/***********/
	private static AlipayClient alipayClient = null;
	
	/***********/
	private static AlipayClient alipayJsClient = null;
	
	/*****APP请求实体******/
	private static AlipayTradeAppPayModel model = null;
	
	/******APP请求request*****/
	private static AlipayTradeAppPayRequest request = null;
	
	/******生活号支付请求request*****/
	private static AlipayTradeCreateRequest jsRequest = null;
	
	public static AlipayClient getInstanceClient() {
		if(alipayClient==null) {
			alipayClient = new DefaultAlipayClient(
					  BasicInfo.URL, 
					  BasicInfo.APPID, 
					  BasicInfo.RSA_PRIVATE_KEY, 
					  BasicInfo.FORMAT, 
					  BasicInfo.CHARSET, 
					  BasicInfo.ALIPAY_PUBLIC_KEY,
					  BasicInfo.SIGNTYPE);
			    return alipayClient;
			}else {
				return alipayClient;
			}
	}
	
	
	public static AlipayClient getInstanceJsClient() {
		if(alipayJsClient==null) {
			alipayJsClient = new DefaultAlipayClient(
					BasicInfo.URL, 
					BasicInfo.APPID, 
					BasicInfo.RSA_PRIVATE_KEY, 
					BasicInfo.FORMAT, 
					  "GBK", 
					BasicInfo.ALIPAY_PUBLIC_KEY,
					BasicInfo.SIGNTYPE);
			    return alipayJsClient;
			}else {
				return alipayJsClient;
			}
	}

	
	public static AlipayTradeAppPayModel getInstanceModel() {
		if(model==null) {
			    model = new AlipayTradeAppPayModel();
			    //设置未付款支付宝交易的超时时间，一旦超时，该笔交易就会自动被关闭
			    model.setTimeoutExpress("30m");
			    //销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
			    model.setProductCode("QUICK_MSECURITY_PAY");				    		
			    return model;
			}else {
				return model;
			}
	}
	
	
	public static AlipayTradeAppPayRequest getInstanceReq() {
		if(request==null) {
			    request = new AlipayTradeAppPayRequest();
			    request.setNotifyUrl(BasicInfo.notify_url);	    		
			    return request;
			}else {
				return request;
			}
	}
	
	public static AlipayTradeCreateRequest getInstanceJsReq() {
		if(jsRequest==null) {
				jsRequest = new AlipayTradeCreateRequest();
				jsRequest.setNotifyUrl(BasicInfo.notify_url);	    		
			    return jsRequest;
			}else {
				return jsRequest;
			}
	}
	
}
