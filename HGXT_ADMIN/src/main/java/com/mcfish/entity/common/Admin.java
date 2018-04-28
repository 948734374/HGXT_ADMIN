package com.mcfish.entity.common;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员
 * @author ZhouXiaobing
 * @date 2018年3月27日 上午8:35:40 
 * @version 1.0
 */
public class Admin implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2245424844077560392L;
	
	
	private int id;
	private int role_id;		//色角ID
	private String name;		//用户姓名
	private String account;		//登陆账号
	private String head;		//管理员头像
	private int sex;			//理员管性别：0-男 1-女
	private String type;		//管理员类别：0-普通管理 1-超级管理员
	private String phone;		//手机
	private String password;	//密码
	private String comment;		//备注列
	private int status;			//状态 0-正常     1-冻结
	private Date create_time;	//注册时间
	private Long total;			//统计数量
	
	
	private String role;		//角色名称
	private String views;		//查看菜单权限，如100,101,102
	private String edits;		//'编辑菜单权限，如：101,102
	private String rComment;	//角色表备注
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getViews() {
		return views;
	}
	public void setViews(String views) {
		this.views = views;
	}
	public String getEdits() {
		return edits;
	}
	public void setEdits(String edits) {
		this.edits = edits;
	}
	public String getrComment() {
		return rComment;
	}
	public void setrComment(String rComment) {
		this.rComment = rComment;
	}
}
