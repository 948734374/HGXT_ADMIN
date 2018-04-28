package com.mcfish.entity.common;

import java.io.Serializable;
import java.util.Date;

/**
  * 系统异常记录实体类
  * @author ZhouXiaobing
  * @date 2018年3月23日 下午7:28:59 
  * @version 1.0
  */
public class TbSystemError implements Serializable {

	private static final long serialVersionUID = -4665050884616237666L;

	
	private int id;
	private int user_id;		//操作用户
	private String req_ip;		//用户请求IP
	private String method;		//异常方法名
	private String error;		//异常详细堆栈信息
	private int type;			//0-系统异常  1-自定义异常
	private int level;			//错误级别（1-10）等级越高越严重
	private String comment;		//备注
	private Date crate_time;	//创建时间
	
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
	public String getReq_ip() {
		return req_ip;
	}
	public void setReq_ip(String req_ip) {
		this.req_ip = req_ip;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCrate_time() {
		return crate_time;
	}
	public void setCrate_time(Date crate_time) {
		this.crate_time = crate_time;
	}
}
