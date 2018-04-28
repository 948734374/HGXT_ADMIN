package com.mcfish.entity.common;

import java.io.Serializable;

/**
 * 菜单
 * @author ZhouXiaobing
 * @date 2018年3月27日 下午3:29:34 
 * @version 1.0
 */
public class Menu implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 2464398518446667675L;

	private Integer id;		//主键
	private String icon;	//图标名称
	private String name;	//菜单名称
	private String link;	//跳转链接
	private int order_num;	//排序序号
	private int status;		//是否显示
	private String create_time;//创建时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getOrder_num() {
		return order_num;
	}

	public void setOrder_num(int order_num) {
		this.order_num = order_num;
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

	@Override
	public String toString() {
		return "Menu [id=" + id + ", icon=" + icon + ", name=" + name
				+ ", link=" + link + ", order_num=" + order_num + ", status="
				+ status + ", create_time=" + create_time + "]";
	}
}
