package com.mcfish.entity.common;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author ZhouXiaobing
 * @date 2018年3月31日 下午3:26:20 
 * @version 1.0
 */
public class Agent implements Serializable {

	//TODO 等待具体的模块开发
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3105555444038268234L;

	private Integer id;
	private Integer father_id; //上级id

	private String name;
	/** 绑定电话 */
	private String phone;
	/** 绑定邮箱 */
	private String email;
	/** 登录密码，由平台分配 */
	private String password;

	private String head;
	/** 代理省 */
	private String prov;
	/** 代理城市 */
	private String city;

	/** 代理商提成比例：1-100 */
	private Byte proportion;

	private Integer money;

	private Integer total;

	private Byte status;

	private Date createTime;
	private Date create_time;
	
	private int level;
	
	private String fatherName;
	
	private int fatherPro;	
	
	
	
	public int getFatherPro() {
		return fatherPro;
	}

	public void setFatherPro(int fatherPro) {
		this.fatherPro = fatherPro;
	}
	
	
	public Integer getFather_id() {
		return father_id;
	}

	public void setFather_id(Integer father_id) {
		this.father_id = father_id;
	}
	
	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	private int agentCount; //代理商数量
	private int shopCount; //商家数量
	private int cabinetCount;  //机柜数量
	
	

	public int getAgentCount() {
		return agentCount;
	}

	public void setAgentCount(int agentCount) {
		this.agentCount = agentCount;
	}

	public int getShopCount() {
		return shopCount;
	}

	public void setShopCount(int shopCount) {
		this.shopCount = shopCount;
	}

	public int getCabinetCount() {
		return cabinetCount;
	}

	public void setCabinetCount(int cabinetCount) {
		this.cabinetCount = cabinetCount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head == null ? null : head.trim();
	}

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov == null ? null : prov.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public Byte getProportion() {
		return proportion;
	}

	public void setProportion(Byte proportion) {
		this.proportion = proportion;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}