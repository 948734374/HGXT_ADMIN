package com.mcfish.util.alipay;

/**
 * 统一收单交易创建商品实体
 * 
 * @author wuhong 2017年9月4日 下午7:32:49
 * @version 源佳 1.0
 */
public class AlipayTradeCreateGoodModel {

	/** 商品的编号(必填) ***/
	private String goods_id;
	/*** 商品名称(必填) **/
	private String goods_name;
	/*** 商品数量(必填) **/
	private Integer quantity;
	/*** 商品单价，单位为元(必填) **/
	private Double price;
	/** 商品类目 ***/
	private String goods_category;
	/*** 商品描述信息 **/
	private String body;
	/*** 商品的展示地址 **/
	private String show_url;

	public AlipayTradeCreateGoodModel() {
		super();
	}

	public AlipayTradeCreateGoodModel(String goods_id, String goods_name, Integer quantity, Double price,
			String goods_category, String body, String show_url) {
		super();
		this.goods_id = goods_id;
		this.goods_name = goods_name;
		this.quantity = quantity;
		this.price = price;
		this.goods_category = goods_category;
		this.body = body;
		this.show_url = show_url;
	}

	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getGoods_category() {
		return goods_category;
	}

	public void setGoods_category(String goods_category) {
		this.goods_category = goods_category;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getShow_url() {
		return show_url;
	}

	public void setShow_url(String show_url) {
		this.show_url = show_url;
	}

}
