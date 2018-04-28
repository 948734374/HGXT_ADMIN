package com.mcfish.entity.common;

import java.io.Serializable;

/**
 * 系统配置实体类
 * @author ZhouXiaobing
 * @date 2018年3月27日 下午5:39:12 
 * @version 1.0
 */
public class System implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -5623613823619274377L;

	private int id;
	private String key;			//关键词KEY
	private String value;   	//关键词的值
	private String comment; 	//备注说明
	private int status;			//0-有效 1-无效
	private String create_time; //创建时间
	private String key_name; 	//关键字名字
	private Long total;			//统计数量
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
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
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getKey_name() {
		return key_name;
	}
	public void setKey_name(String key_name) {
		this.key_name = key_name;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "SystemConfig [id=" + id + ", key=" + key + ", value=" + value + ", comment=" + comment + ", status="
				+ status + ", create_time=" + create_time + ", key_name=" + key_name + ", total=" + total + "]";
	}
}
