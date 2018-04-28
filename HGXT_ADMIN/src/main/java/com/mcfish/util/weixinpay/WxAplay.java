package com.mcfish.util.weixinpay;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.util.IOUtils;

import com.mcfish.util.BasicInfo;

/**
 * @author TangLin
 * @date 2017年9月11日 下午5:10:11 
 * @version 1.0
 */

public class WxAplay {
	
	/**
     * 发送请求
     * */
    @SuppressWarnings("unused")
	private static String ssl(String url,String data,HttpServletRequest req){
        StringBuffer message = new StringBuffer();
        try {
            KeyStore keyStore  = KeyStore.getInstance("PKCS12");
          //  String certFilePath = "D:/certs/apiclient_cert.p12";
          //  FileInputStream instream = new FileInputStream(new File(certFilePath));
          //  keyStore.load(instream, mchId.toCharArray());
            SSLContext sslcontext = initSSLContext(req);
            SSLConnectionSocketFactory sslsf = 
            		new SSLConnectionSocketFactory(sslcontext, 
            				new String[] { "TLSv1" }, 
            				null, 
            				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            HttpPost httpost = new HttpPost(url);
            httpost.addHeader("Connection", "keep-alive");
            httpost.addHeader("Accept", "*/*");
            httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpost.addHeader("Host", "api.mch.weixin.qq.com");
            httpost.addHeader("X-Requested-With", "XMLHttpRequest");
            httpost.addHeader("Cache-Control", "max-age=0");
            httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
            httpost.setEntity(new StringEntity(data, "UTF-8"));
            System.out.println("executing request" + httpost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httpost);
            try {
                HttpEntity entity = response.getEntity();
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    System.out.println("Response content length: " + entity.getContentLength());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"));
                    String text;
                    while ((text = bufferedReader.readLine()) != null) {
                        message.append(text);
                    }
                }
                EntityUtils.consume(entity);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                response.close();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } 
        return message.toString();
    }
    
    /**
     * 生成签名
     * */
    public static String createSendRedPackOrderSign(SendRedPack redPack){
        StringBuffer sign = new StringBuffer();
        sign.append("act_name=").append(redPack.getAct_name());
        sign.append("&client_ip=").append(redPack.getClient_ip());
        sign.append("&mch_billno=").append(redPack.getMch_billno());
        sign.append("&mch_id=").append(redPack.getMch_id());
        sign.append("&nonce_str=").append(redPack.getNonce_str());
        sign.append("&re_openid=").append(redPack.getRe_openid());
        sign.append("&remark=").append(redPack.getRemark());
        sign.append("&send_name=").append(redPack.getSend_name());
        sign.append("&total_amount=").append(redPack.getTotal_amount());
        sign.append("&total_num=").append(redPack.getTotal_num());
        sign.append("&wishing=").append(redPack.getWishing());
        sign.append("&wxappid=").append(redPack.getWxappid());
        sign.append("&key=").append(BasicInfo.MchKey);
        return DigestUtils.md5Hex(sign.toString()).toUpperCase();
    }
    
  
    /**
     * 封装参数到实体中
     * @author TangLin 
     * @date 2017年9月12日 上午10:37:23 
     * @param   
     * @return void
     */
    public static Map<String, Object>  WeChatUtils(String order,String re_openid,int amount, HttpServletRequest req ){
    	Map<String, Object> mp = new HashMap<String, Object>();
    	SendRedPack sendRedPack = new SendRedPack();
        sendRedPack.setNonce_str(order);
        sendRedPack.setMch_id(BasicInfo.MchId);
        sendRedPack.setMch_billno(order);
        sendRedPack.setWxappid(BasicInfo.appID);
        sendRedPack.setSend_name(BasicInfo.Send_Name);
        sendRedPack.setTotal_num(1);
        sendRedPack.setAct_name("申请提现");
        sendRedPack.setWishing("红包");
        sendRedPack.setRemark("提现红包");
        sendRedPack.setClient_ip("8.8.8.8");
        
        sendRedPack.setRe_openid(re_openid);
        sendRedPack.setTotal_amount(amount);
        String sign = createSendRedPackOrderSign(sendRedPack);
        sendRedPack.setSign(sign);
        
        try{
        	 XMLUtil xmlUtil= new XMLUtil();
             xmlUtil.xstream().alias("xml", sendRedPack.getClass());
            
             String xml = xmlUtil.xstream().toXML(sendRedPack);
             String response = ssl(BasicInfo.httpurl, xml,req);
             System.out.println(response);
             Map<String, String> map = xmlUtil.parseXml(response);
             if("SUCCESS".equals(map.get("result_code"))){
            	 mp.put("stu", true);
            	 return mp;
             }else{
            	 mp.put("stu", false);
            	 mp.put("errMsg", map.get("return_msg"));
            	 return mp;
             }
        }catch (Exception e) {
            throw new RuntimeException("提现失败！", e);
        }
       
    }
 
   
   
   private static SSLContext sslContext;
   public static SSLContext initSSLContext(HttpServletRequest req) {
       FileInputStream inputStream = null;
       try {
           inputStream = new FileInputStream(new File(req.getSession().getServletContext().
					 getRealPath("") + "/WEB-INF"+BasicInfo.KeyPath));
       } catch (IOException e) {
           throw new RuntimeException("证书不正确！", e);
       }

       try {
           KeyStore keystore = KeyStore.getInstance("PKCS12");
           char[] partnerId2charArray = BasicInfo.MchId.toCharArray();
           keystore.load(inputStream, partnerId2charArray);
           sslContext = SSLContexts.custom().loadKeyMaterial(keystore, partnerId2charArray).build();
           return sslContext;
       } catch (Exception e) {
           throw new RuntimeException("秘钥不正确！", e);
       } finally {
           IOUtils.closeQuietly(inputStream);
       }
   }
   
}
