package com.mcfish.entity.common;

import java.io.Serializable;
import java.util.Date;


/**
 * 关于我们实体类
 * @author ZhouXiaobing
 * @date 2018年3月27日 下午9:28:22 
 * @version 1.0
 */
public class About implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 4600159556050770717L;
	
	private int id;				//主键
	private int type;			//内置说明 1-是 0-否
	private String code;		//关键字
	private String title;		//内容标注
	private String content;		//内容
	private Date create_time;	//创建时间
	private String website;		//官网地址
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "About [id=" + id + ", type=" + type + ", code=" + code + ", title=" + title + ", content=" + content
				+ ", create_time=" + create_time + ", website=" + website + ", total=" + total + "]";
	}
}
