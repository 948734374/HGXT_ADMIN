package com.mcfish.entity.common;

import java.io.Serializable;
import java.sql.Date;


/**
 * App版本实体类
 * @author ZhouXiaobing
 * @date 2018年3月27日 下午8:22:36 
 * @version 1.0
 */
public class Version implements Serializable{
	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 3941417832242363510L;
	
	private int id ;			//主键id
	private int type;			//APP类型 0-Android 1-IOS
	private String version;		//版本信息
	private String comment;		//备注
	private int is_debug;		//调试开关
	private int is_force;		//是否强制更新
	private String download;	//下载地址
	private Date create_time;	//创建时间
	private Long total;			//统计数量
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getIs_debug() {
		return is_debug;
	}
	public void setIs_debug(int is_debug) {
		this.is_debug = is_debug;
	}
	public int getIs_force() {
		return is_force;
	}
	public void setIs_force(int is_force) {
		this.is_force = is_force;
	}
	public String getDownload() {
		return download;
	}
	public void setDownload(String download) {
		this.download = download;
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
		return "Version [id=" + id + ", type=" + type + ", version=" + version + ", comment=" + comment + ", is_debug="
				+ is_debug + ", is_force=" + is_force + ", download=" + download + ", create_time=" + create_time
				+ ", total=" + total + "]";
	}
}
