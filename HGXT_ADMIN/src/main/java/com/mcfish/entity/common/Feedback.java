package com.mcfish.entity.common;

import java.io.Serializable;
import java.util.Date;

/**
 * 意见反馈实体类
 * @author ZhouXiaobing
 * @date 2018年3月26日 下午5:43:17 
 * @version 1.0
 */
public class Feedback implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 4195992199576117664L;
	
	private int id;				// 主键
	private int user_id;		//用户ID
	private String name;		//用户昵称
	private String phone;		//用户电话
	private String title;		//建议标题
	private String content;		//建议内容
	private int status;			//处理状态：0-未处理 1-采纳 2-拒绝
	private String comment;		//处理意见
	private Date create_time;	//创建时间
	private Long total;			//统计总数
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
	
}
