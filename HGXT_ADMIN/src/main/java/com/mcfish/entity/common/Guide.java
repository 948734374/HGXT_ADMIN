package com.mcfish.entity.common;

import java.io.Serializable;
import java.util.Date;


/**
 * APP引导页管理实体类
 * @author ZhouXiaobing
 * @date 2018年3月29日 下午3:54:42 
 * @version 1.0
 */
public class Guide implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8528043259939856609L;
	
	
	private int id;
	private String image;		//引导页图片
	private String comment;		//说明文字
	private int status;			//状态：0-正常 1-隐藏
	private int	order_num;		//排序字段
	private Date create_time;
	private Long total;			//统计数量
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
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
		return "Guide [id=" + id + ", image=" + image + ", comment=" + comment + ", status=" + status + ", order_num="
				+ order_num + ", create_time=" + create_time + ", total=" + total + "]";
	}
}
