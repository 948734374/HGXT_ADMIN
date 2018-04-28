package com.mcfish.util.weixin;
/**
 * @author TangLin
 * @date 2017年8月5日 下午1:49:43 
 * @version 1.0
 */
public class AccessToken {

    // 获取到的凭证    
    private String token;    
    // 凭证有效时间，单位：秒    
    private int expiresIn;  
      
    public String getToken() {  
        return token;  
    }  
    public void setToken(String token) {  
        this.token = token;  
    }  
    public int getExpiresIn() {  
        return expiresIn;  
    }  
    public void setExpiresIn(int expiresIn) {  
        this.expiresIn = expiresIn;  
    }  
}
