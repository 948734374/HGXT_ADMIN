package com.mcfish.util.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenPublicQrcodeCreateRequest;
import com.alipay.api.response.AlipayOpenPublicQrcodeCreateResponse;

/**
 * 带参二维码接口调用
 * @author tanglin
 *
 */
public class AlipayOpenGeneralize {

	// 带参推广二维码接口
	public static String qrcodeCreate(int secen_id) {
		
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayServiceEnvConstants.ALIPAY_GATEWAY,AlipayServiceEnvConstants.APP_ID,
		AlipayServiceEnvConstants.PRIVATE_KEY,"json","GBK",AlipayServiceEnvConstants.ALIPAY_PUBLIC_KEY,AlipayServiceEnvConstants.SIGN_TYPE);
		AlipayOpenPublicQrcodeCreateRequest request = new AlipayOpenPublicQrcodeCreateRequest();
		request.setBizContent("{" +
		"\"code_info\":{" +
		"\"scene\":{" +
		"\"scene_id\":" + secen_id +
		"      }," +
//		"\"goto_url\":\"http://www.******.com\"" +
		"    }," +
		"\"code_type\":\"PERM\"," +
		"\"show_logo\":\"Y\"" +
		"  }");
		AlipayOpenPublicQrcodeCreateResponse response = null;
		try {
			response = alipayClient.execute(request);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		if(response.isSuccess()){
		return response.getCodeImg();
		} else {
		System.out.println("调用失败");
		return "";
		}
	}

	/**
	 * 二维码生成接口测试
	 * @author TangLin 
	 * @date 2017年8月8日 下午4:29:47 
	 * @param   
	 * @return void
	 */
	public static void main(String[] args) {
		System.out.println(qrcodeCreate(2));
	}
}
