package com.mcfish.entity.common;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 * @author ZhouXiaobing
 * @date 2018年3月24日 下午4:08:59 
 * @version 1.0
 */
public class User implements Serializable{
	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -8165818632919833725L;

	private int id;							//用户ID
	private int father_id;					//邀请者ID
	private String name;					//用户昵称
	private String head;					//用户的头像
	private int sex;						//用户性别：0-男 1-女
	private int identity;					//用户身份位：0-普通用户 1-我是商家 2-我是小二
	private String phone;					//绑定的手机号
	private String password;				//用户登陆密码
	private String openid_wx;				//绑定微信登陆
	private String openid_qq;				//绑定腾讯QQ
	private String openid_wb;				//绑定新浪微博账号
	private String openid_alipay;			//绑定支付宝生活号
	private String openid_facebook;			//绑定Facebook账号
	private String openid_google;			//绑定Google账号
	private Date birthday;					//用户生日
	private String email;					//用户邮箱
 	private int money;						//用户钱包余额：单位分
	private int deposit;					//用户押金
	private int point;						//积分
	private String alipay_name;				//提现：支付宝名称
	private String alipay_account;			//提现：支付宝账号
	private String token;					//用户登陆态
	private String comment;					//备注，冻结的时候可以备注原因
	private int status;						//用户状态：0-正常 1-冻结
	private Date login_time;				//用户登陆时间
	private Date create_time;				//注册时间
	private Long total;						//统计数量时使用
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFather_id() {
		return father_id;
	}
	public void setFather_id(int father_id) {
		this.father_id = father_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getIdentity() {
		return identity;
	}
	public void setIdentity(int identity) {
		this.identity = identity;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOpenid_wx() {
		return openid_wx;
	}
	public void setOpenid_wx(String openid_wx) {
		this.openid_wx = openid_wx;
	}
	public String getOpenid_qq() {
		return openid_qq;
	}
	public void setOpenid_qq(String openid_qq) {
		this.openid_qq = openid_qq;
	}
	public String getOpenid_wb() {
		return openid_wb;
	}
	public void setOpenid_wb(String openid_wb) {
		this.openid_wb = openid_wb;
	}
	public String getOpenid_alipay() {
		return openid_alipay;
	}
	public void setOpenid_alipay(String openid_alipay) {
		this.openid_alipay = openid_alipay;
	}
	public String getOpenid_facebook() {
		return openid_facebook;
	}
	public void setOpenid_facebook(String openid_facebook) {
		this.openid_facebook = openid_facebook;
	}
	public String getOpenid_google() {
		return openid_google;
	}
	public void setOpenid_google(String openid_google) {
		this.openid_google = openid_google;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getDeposit() {
		return deposit;
	}
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getAlipay_name() {
		return alipay_name;
	}
	public void setAlipay_name(String alipay_name) {
		this.alipay_name = alipay_name;
	}
	public String getAlipay_account() {
		return alipay_account;
	}
	public void setAlipay_account(String alipay_account) {
		this.alipay_account = alipay_account;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getLogin_time() {
		return login_time;
	}
	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", father_id=" + father_id + ", name=" + name + ", head=" + head + ", sex=" + sex
				+ ", identity=" + identity + ", phone=" + phone + ", password=" + password + ", openid_wx=" + openid_wx
				+ ", openid_qq=" + openid_qq + ", openid_wb=" + openid_wb + ", openid_alipay=" + openid_alipay
				+ ", openid_facebook=" + openid_facebook + ", openid_google=" + openid_google + ", birthday=" + birthday
				+ ", email=" + email + ", money=" + money + ", deposit=" + deposit + ", point=" + point
				+ ", alipay_name=" + alipay_name + ", alipay_account=" + alipay_account + ", token=" + token
				+ ", comment=" + comment + ", status=" + status + ", login_time=" + login_time + ", create_time="
				+ create_time + ", total=" + total + "]";
	}
	  
}
