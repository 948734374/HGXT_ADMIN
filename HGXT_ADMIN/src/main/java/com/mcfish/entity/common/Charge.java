package com.mcfish.entity.common;

import java.io.Serializable;
import java.util.Date;

/**
 * 充值金额配置实体类
 * @author ZhouXiaobing
 * @date 2018年3月27日 下午8:01:05 
 * @version 1.0
 */
public class Charge implements Serializable{

	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -5863817615949982745L;
	

	private int id;  			//主键
	private int amount;  		//充值金额，单位分
	private int charge;  		//实际到帐，单位分
	private int give;  			//赠送金额，单位分
	private String comment;  	//可选：备注
	private Date create_time;	//创建时间
	private Long total;			//统计数量
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getCharge() {
		return charge;
	}
	public void setCharge(int charge) {
		this.charge = charge;
	}
	public int getGive() {
		return give;
	}
	public void setGive(int give) {
		this.give = give;
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
	
	@Override
	public String toString() {
		return "Charge [id=" + id + ", amount=" + amount + ", charge=" + charge + ", give=" + give + ", comment="
				+ comment + ", create_time=" + create_time + ", total=" + total + "]";
	}
}
