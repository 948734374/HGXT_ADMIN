package com.mcfish.entity.common;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 劳务公司
 * @author ZhangYichi
 * @data 2018年4月28日 上午11:12:24 
 */
public class Labour implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3665722368019823940L;
	
	/*
	 * tb_labour
	 */
	
	private int id;
	private String name;            //劳务公司名称
	private String logo;            //劳务公司LOGO
	private String phone;           //手机号
	private String password;        //密码
	private String prov;            //省
	private String city;            //城市
	private String zone;            //区
	private String address;         //详细地址
	private String contractor;      //联系人
	private int status;             //状态   0-冻结   1-正常
	private Timestamp create_time;  //创建时间
	
	private Long total;             //获取总数
	
	
	
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProv() {
		return prov;
	}
	public void setProv(String prov) {
		this.prov = prov;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContractor() {
		return contractor;
	}
	public void setContractor(String contractor) {
		this.contractor = contractor;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	@Override
	public String toString() {
		return "Labour [id=" + id + ", name=" + name + ", logo=" + logo + ", phone=" + phone + ", password=" + password
				+ ", prov=" + prov + ", city=" + city + ", zone=" + zone + ", address=" + address + ", contractor="
				+ contractor + ", status=" + status + ", create_time=" + create_time + ", total=" + total + "]";
	}

	
	
}
