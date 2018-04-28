package com.mcfish.entity.common;

import java.io.Serializable;
import java.util.Date;


/**
 * 操作记录
 * @author ZhouXiaobing
 * @date 2018年3月24日 上午8:00:20 
 * @version 1.0
 */
public class OperateLog implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 7894329365999297741L;

	private int id;
	private int admin_id;			//管理员id
	private String ip;				//操作ip
	private String action;			//行为名称
	private String comment;			//操作内容
	private String creator;			//理员管名称
	private Date create_time;		//操作时间
	private Long total;				//统计总数
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
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
