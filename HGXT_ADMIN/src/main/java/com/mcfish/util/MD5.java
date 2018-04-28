package com.mcfish.util;

import java.security.MessageDigest;

public class MD5 {

	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return str;
	}
	
	
	/**
	 * 公司MD5加密
	 * @author ZhouXiaobing 
	 * @date 2018年3月31日 下午3:02:56 
	 * @param str
	 * @return
	 */
	public static String pwdMd5(String str) {
		return MD5.md5(str + "mCfIsHcOmPaNyAdMiN");
	}
	
	public static void main(String[] args) {
		System.out.println(md5("31119@qq.com"+"123456"));
		System.out.println(md5("1a32s1d32ad"));
	}
}
