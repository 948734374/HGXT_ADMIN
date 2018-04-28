package com.mcfish.util.alipay;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.stereotype.Component;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.ZhimaMerchantOrderRentCancelModel;
import com.alipay.api.domain.ZhimaMerchantOrderRentCompleteModel;
import com.alipay.api.domain.ZhimaMerchantOrderRentQueryModel;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.request.ZhimaMerchantOrderRentCancelRequest;
import com.alipay.api.request.ZhimaMerchantOrderRentCompleteRequest;
import com.alipay.api.request.ZhimaMerchantOrderRentQueryRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.alipay.api.response.ZhimaMerchantOrderRentCancelResponse;
import com.alipay.api.response.ZhimaMerchantOrderRentCompleteResponse;
import com.alipay.api.response.ZhimaMerchantOrderRentQueryResponse;
import com.mcfish.util.BasicInfo;
import com.mcfish.util.Logger;

/**
 * 支付宝支付接口
 * @author Tanglin
 * @date 2017年9月11日 上午10:52:13 
 */
@Component
public class AliPay {
	
	protected static Logger log = Logger.getLogger(AliPay.class);
	
	
	/**
	 * 支付宝转账
	 * @author TangLin 
	 * @date 2017年9月11日 上午10:55:13 
	 * @param   
	 * @return boolean
	 */
	public static Map<String, Object> appAplay(String sno,String user_name,double money){
		 Map<String, Object> mp = new HashMap<String, Object>();
		 try {
	    	 AlipayClient alipayClient = new DefaultAlipayClient(BasicInfo.URL,
						BasicInfo.APPID,
						BasicInfo.RSA_PRIVATE_KEY,
						BasicInfo.FORMAT,
						BasicInfo.CHARSET,
						BasicInfo.ALIPAY_PUBLIC_KEY,
						BasicInfo.SIGNTYPE);
	    	 
	    	 
	    	 AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
	    	 request.setBizContent("{" +
	    	 "\"out_biz_no\":"+sno+"," +
	    	 "\"payee_type\":\"ALIPAY_LOGONID\"," +
	    	 "\"payee_account\":"+user_name+"," +
	    	 "\"amount\":"+money+
	    	 "  }");
	    	 AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);
	    	 if(response.isSuccess()){
	    		 System.out.println("调用成功");
	    		 mp.put("stu", true);
	    		 return mp;
	    	 
	    	 } else {
	    		 System.out.println("调用失败");
	    		 mp.put("stu", false);
	    		 mp.put("errMsg", response.getSubMsg());
	    		 return mp;
	    	 }
	    	 
	     }catch (Exception e) {
	    	  throw new RuntimeException("提现失败！", e);
	 		}
	}
	
	
	/**
	 * 归还免押金订单
	 * @param orderNo 芝麻免押金订单号
	 * @param payType 类型 RENT:租金  DAMAGE:赔偿金
	 * @param money 扣除金额 保留两位小数
	 * @return
	 * @throws Exception
	 */
	public static Map<Object, Object> ZhimaMerchantOrderOver(String orderNo,String payType,String money) throws Exception{		
		try {		
			Map<Object, Object> res = new HashMap<Object, Object>();
			
			AlipayClient alipayClient = AlipayInit.getInstanceJsClient();
			ZhimaMerchantOrderRentCompleteRequest  request = new ZhimaMerchantOrderRentCompleteRequest();
			
			ZhimaMerchantOrderRentCompleteModel model = new ZhimaMerchantOrderRentCompleteModel();
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			
			model.setOrderNo(orderNo);
			model.setProductCode("w1010100000000002858");
			model.setPayAmountType(payType);
			model.setRestoreTime(format.format(date));
			model.setPayAmount(money);
			request.setBizModel(model);
			ZhimaMerchantOrderRentCompleteResponse response = alipayClient.execute(request);
			if(response.isSuccess()){
				if(response.getMsg().equals("Success")){
					res.put("user_id", response.getUserId());
					res.put("alipay_fund_order_no", response.getAlipayFundOrderNo());
					res.put("msg", response.getMsg());
					return res;
				}
				res.put("msg", "Faild");
				return res;
			}else {
				log.error("归还免押金订单请求失败:"+response.getMsg()+":"+response.getCode()+"=="+response.getSubCode());
				return null;
			}			
		} catch (AlipayApiException e) {
			log.error("归还免押金订单请求异常:",e);
			return null;
		}
	}
	
	
	
	@Test
	public void test() throws Exception{
		String order = ZhimaMerchantOrderCheck("201801031108111807").getOrderNo();
		System.out.println(order);
		System.out.println(ZhimaMerchantOrderCheck("201801031108111807").getPayAmount());
		//System.out.println(ZhimaMerchantOrderCancel(order));
		//ZhimaMerchantOrderOver(order,"RENT","0.01");
	}
	
	
	/**
	 * 免押金订单查询接口
	 * @param outOrderNo
	 * @return
	 * @throws Exception
	 */
	public static ZhimaMerchantOrderRentQueryResponse ZhimaMerchantOrderCheck(String outOrderNo) throws Exception{		
		try {			
			AlipayClient alipayClient = AlipayInit.getInstanceJsClient();
			ZhimaMerchantOrderRentQueryRequest request = new ZhimaMerchantOrderRentQueryRequest();
			
			ZhimaMerchantOrderRentQueryModel model = new ZhimaMerchantOrderRentQueryModel();
			model.setOutOrderNo(outOrderNo);
			model.setProductCode("w1010100000000002858");
			
			request.setBizModel(model);
			ZhimaMerchantOrderRentQueryResponse response = alipayClient.execute(request);
			if(response.isSuccess()){
				if(response.getMsg().equals("Success")){
					return response;
				}
				return null;
			}else {
				log.error("查询免押金订单请求失败:"+response.getMsg()+":"+response.getCode()+"=="+response.getSubCode());
				return null;
			}			
		} catch (AlipayApiException e) {
			log.error("查询免押金订单请求异常:",e);
			return null;
		}
	}
	
	
	/**
	 * 撤销信用订单
	 * @param orderNo 信用订单号
	 * @return
	 * @throws Exception
	 */
	public static String ZhimaMerchantOrderCancel(String orderNo) throws Exception{		
		try {			
			AlipayClient alipayClient = AlipayInit.getInstanceJsClient();
			ZhimaMerchantOrderRentCancelRequest request = new ZhimaMerchantOrderRentCancelRequest();
			
			ZhimaMerchantOrderRentCancelModel model = new ZhimaMerchantOrderRentCancelModel();
			model.setOrderNo(orderNo);
			model.setProductCode("w1010100000000002858");
			
			request.setBizModel(model);
			ZhimaMerchantOrderRentCancelResponse  response = alipayClient.execute(request); 
			if(response.isSuccess()){
				return response.getMsg();
			}else {
				log.error("撤销免押金订单请求失败:"+response.getMsg()+":"+response.getCode()+"=="+response.getSubCode());
				return "";
			}			
		} catch (AlipayApiException e) {
			log.error("撤销免押金订单请求异常:",e);
			return "";
		}
	}
	
	
}
