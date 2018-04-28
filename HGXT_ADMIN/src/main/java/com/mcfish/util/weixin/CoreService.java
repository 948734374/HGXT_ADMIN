package com.mcfish.util.weixin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.mcfish.util.BasicInfo;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * @author TangLin
 * @date 2017年8月5日 上午9:52:41 
 * @version 1.0
 */
public class CoreService {
	
	/**
	 * 生成微信二维码
	 * @author TangLin 
	 * @date 2017年8月7日 上午9:35:21 
	 * @param   
	 * @return String
	 */
	public static Map<String , String> getWxQR(int sence_id){
		String addr = "";
		String appid = BasicInfo.appID;  //公众号账号
		String appsecret = BasicInfo.APP_AppSecret;  //公众号秘钥
		String token = CoreService.getAccessToken(appid, appsecret);  //获取access_token
		
		Map<String , String > parMap = new HashMap<>();
		try {
			
			//生成永久二维码
			Map<String , String> map = CoreService.createForeverTicket(token, sence_id);

			String scen_id = map.get("scene_id_wx");
			String ss = map.get("qrUrl");
			
			addr = showQrcode(ss);
			parMap.put("qrParam", scen_id);
			parMap.put("qrAddr", addr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parMap;
	}
	
	
	/**
	 * 获取access_token 
	 * @author TangLin 
	 * @date 2017年8月5日 下午1:58:33 
	 * @param   
	 * @return String
	 */ 
	public static String getAccessToken(String appid, String appsecret) {  
	    String result = HttpRequestUtil.getAccessToken(appid,appsecret);  
	   // System.out.println(result);
	    JSONObject jsonObject = JSONObject.fromObject(result);  
	    if (null != jsonObject) {  
	        try {  
	            result = jsonObject.getString("access_token");  
	        } catch (JSONException e) {  
	        	System.out.println("获取token失败！");
	        }  
	    }  
	    return result;  
	}  
	
	
	/**
	 * 创建永久二维码(数字)  
	 * @author TangLin 
	 * @date 2017年8月5日 下午1:58:54 
	 * @param   
	 * @return String
	 * @throws Exception 
	 */
	public static Map<String , String> createForeverTicket(String accessToken, int sceneId) throws Exception {  
		
		//将 accessToken和sceneId放入map中
	    TreeMap<String,String> params = new TreeMap<String,String>();  
	    params.put("access_token", accessToken);  
	    
	    Map<String,Integer> intMap = new HashMap<String,Integer>();  
	    intMap.put("scene_id",sceneId);  
	    Map<String,Map<String,Integer>> mapMap = new HashMap<String,Map<String,Integer>>();  
	    mapMap.put("scene", intMap);  
	    //设置创建的的二维码类型， "QR_LIMIT_SCENE"为永久性二维码
	    Map<String,Object> paramsMap = new HashMap<String,Object>();  
	    paramsMap.put("action_name", "QR_LIMIT_SCENE");  
	    paramsMap.put("action_info", mapMap);  
	    String data = new Gson().toJson(paramsMap);  
	    data =  HttpRequestUtil.HttpsDefaultExecute(HttpRequestUtil.POST_METHOD,"https://api.weixin.qq.com/cgi-bin/qrcode/create",params,data);  
	    
	    //将json字符串转为json对象数据
	    com.alibaba.fastjson.JSONObject jsonObj =JSON.parseObject(data); 
        Set<String>  jsonKeySet = jsonObj.keySet();   
        Map<String, String> resultMap = new HashMap<>();  
        Iterator<String> it = jsonKeySet.iterator();    
        while (it.hasNext()) {    
          String key = it.next();    
          resultMap.put(key, jsonObj.getString(key));  
        }  
        //获取到code和二维码的图片地址
	    Map<String , String> map = new HashMap<>();
	    String picUrl = resultMap.get("url");
	    map.put("scene_id_wx", picUrl.substring(23));
	    map.put("qrUrl", resultMap.get("ticket"));
	    return map;
	}
	
	
	
	
	//显示出生成的二维码
	public static String showQrcode(String ticket){
		String showqrcode_path="https://mp.weixin.qq.com/cgi-bin/showqrcode";
		Map<String,String> params = new TreeMap<String,String>();  
	    params.put("ticket", HttpRequestUtil.urlEncode(ticket, HttpRequestUtil.DEFAULT_CHARSET));  
	    try {  
	        showqrcode_path = HttpRequestUtil.setParmas(params,showqrcode_path,"");  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	   return showqrcode_path;
	}
	
	
	
}
