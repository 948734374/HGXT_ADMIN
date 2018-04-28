package com.mcfish.util.alipay;

import java.io.Serializable;

/**
 * 统一收单交易创建实体
 * @author wuhong 2017年9月4日 下午7:29:05
 * @version 源佳 1.0
 */
public class AlipayTradeCreateModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1847959571743936781L;

	/** 商户订单号,64个字符以内、只能包含字母、数字、下划线；需保证在商户端不重复(必须) **/
	private String out_trade_no;

	/** 卖家支付宝用户ID。如果该值为空，则默认为商户签约账号对应的支付宝用户ID **/
	private String seller_id;

	/** 订单总金额，单位为元，精确到小数点后两位(必须) **/
	private Double total_amount;

	/** 可打折金额. **/
	private Double discountable_amount;

	/** 订单标题(必须) **/
	private String subject;

	/** 对交易或商品的描述 **/
	private String body;

	/** 买家的支付宝唯一用户号（2088开头的16位纯数字）,和buyer_logon_id不能同时为空 **/
	private String buyer_id;

	/** 订单包含的商品列表信息.Json格式.其它说明详见：“商品明细说明” **/
	private AlipayTradeCreateGoodModel goods_detail;

	/** 商户操作员编号 **/
	private String operator_id;

	/** 商户门店编号 **/
	private String store_id;

	/** 商户机具终端编号 **/
	private String terminal_id;

	/** 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d **/
	private String timeout_express;

	public AlipayTradeCreateModel() {
		super();
	}

	public AlipayTradeCreateModel(String out_trade_no, String seller_id, Double total_amount,
			Double discountable_amount, String subject, String body, String buyer_id,
			AlipayTradeCreateGoodModel goods_detail, String operator_id, String store_id, String terminal_id,
			String timeout_express) {
		super();
		this.out_trade_no = out_trade_no;
		this.seller_id = seller_id;
		this.total_amount = total_amount;
		this.discountable_amount = discountable_amount;
		this.subject = subject;
		this.body = body;
		this.buyer_id = buyer_id;
		this.goods_detail = goods_detail;
		this.operator_id = operator_id;
		this.store_id = store_id;
		this.terminal_id = terminal_id;
		this.timeout_express = timeout_express;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public Double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(Double total_amount) {
		this.total_amount = total_amount;
	}

	public Double getDiscountable_amount() {
		return discountable_amount;
	}

	public void setDiscountable_amount(Double discountable_amount) {
		this.discountable_amount = discountable_amount;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	public AlipayTradeCreateGoodModel getGoods_detail() {
		return goods_detail;
	}

	public void setGoods_detail(AlipayTradeCreateGoodModel goods_detail) {
		this.goods_detail = goods_detail;
	}

	public String getOperator_id() {
		return operator_id;
	}

	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}

	public String getStore_id() {
		return store_id;
	}

	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	public String getTerminal_id() {
		return terminal_id;
	}

	public void setTerminal_id(String terminal_id) {
		this.terminal_id = terminal_id;
	}

	public String getTimeout_express() {
		return timeout_express;
	}

	public void setTimeout_express(String timeout_express) {
		this.timeout_express = timeout_express;
	}

}
