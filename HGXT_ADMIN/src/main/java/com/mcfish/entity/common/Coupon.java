package com.mcfish.entity.common;

import java.io.Serializable;
import java.util.Date;

/**
 * 优惠券实体类
 * @author ZhouXiaobing
 * @date 2018年3月31日 上午11:10:43 
 * @version 1.0
 */
public class Coupon implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5030918401774684574L;
	
	private int id;				//主键
	private String code;		//优惠券领取码
	private String image;		//优惠卷图片
	private String logo;		//优惠券LOGO
	private String brief;		//优惠卷简介
	private String url;			//点击跳转页
	private int number;			//发布数量，0：无限量
	private int taked;			//已领取数量
	private Date begin;			//开始有效期时间
	private Date end;			//有效期结束时间
	private int status;			//状态：0-上线 1-下线
	private Date create_time;	//发布时间
	private Long total;			//统计数量
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getTaked() {
		return taked;
	}
	public void setTaked(int taked) {
		this.taked = taked;
	}
	public Date getBegin() {
		return begin;
	}
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
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
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", code=" + code + ", image=" + image + ", logo=" + logo + ", brief=" + brief
				+ ", url=" + url + ", number=" + number + ", taked=" + taked + ", begin=" + begin + ", end=" + end
				+ ", status=" + status + ", create_time=" + create_time + ", total=" + total + "]";
	}
}
