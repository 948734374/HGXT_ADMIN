package com.mcfish.util;

import java.util.Random;
import java.util.UUID;

/**
 * 生成订单号规则
 * @author TangLin
 * @date 2017年9月7日 下午5:51:15 
 * @version 1.0
 */

public class IDUtil {
	/**
	 * 订单id生成   时间戳+用户id+随机数
	 * @param uid
	 * @return
	 */
	public static String getOrderNo(Integer uid){
		
		//随机数
		Random random = new Random();
		
		//当前时间（ms）
		String millis = System.currentTimeMillis()+"";
		int index = random.nextInt(millis.length()-5);
		String t1 = millis.substring(index, index+4);

		int t3 = random.nextInt(999);
        //拼接id
		String str = t1+ uid + String.format("%03d", t3);

		return str;
	}
	
	/**
	 * uuid
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}
