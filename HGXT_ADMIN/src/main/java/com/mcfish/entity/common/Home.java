package com.mcfish.entity.common;
import java.io.Serializable;

/**
 * 平台首页
 * @author ZhouXiaobing
 * @date 2018年3月28日 下午2:57:05 
 * @version 1.0
 */
public class Home implements Serializable{
	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -7303582259944242234L;
	
	private Long totalEquipment;	//已投放设备数量
	private Long totalIncome;		//平台总收入
	private Long totalBalance;		//平台总余额
	private Long totalDeposit;		//平台总押金
	private Long totalUsers;		//平台总用户数
	private Long totalOrder;		//平台订单总数
	private Long totalPay;			//用户总的充值金额
	private Long totalWithdraw;		//用户总的提现金额
	
	public Long getTotalEquipment() {
		return totalEquipment;
	}
	public void setTotalEquipment(Long totalEquipment) {
		this.totalEquipment = totalEquipment;
	}
	public Long getTotalIncome() {
		return totalIncome;
	}
	public void setTotalIncome(Long totalIncome) {
		this.totalIncome = totalIncome;
	}
	public Long getTotalBalance() {
		return totalBalance;
	}
	public void setTotalBalance(Long totalBalance) {
		this.totalBalance = totalBalance;
	}
	public Long getTotalDeposit() {
		return totalDeposit;
	}
	public void setTotalDeposit(Long totalDeposit) {
		this.totalDeposit = totalDeposit;
	}
	public Long getTotalUsers() {
		return totalUsers;
	}
	public void setTotalUsers(Long totalUsers) {
		this.totalUsers = totalUsers;
	}
	public Long getTotalOrder() {
		return totalOrder;
	}
	public void setTotalOrder(Long totalOrder) {
		this.totalOrder = totalOrder;
	}
	public Long getTotalPay() {
		return totalPay;
	}
	public void setTotalPay(Long totalPay) {
		this.totalPay = totalPay;
	}
	public Long getTotalWithdraw() {
		return totalWithdraw;
	}
	public void setTotalWithdraw(Long totalWithdraw) {
		this.totalWithdraw = totalWithdraw;
	}
	@Override
	public String toString() {
		return "Home [totalEquipment=" + totalEquipment + ", totalIncome=" + totalIncome + ", totalBalance="
				+ totalBalance + ", totalDeposit=" + totalDeposit + ", totalUsers=" + totalUsers + ", totalOrder="
				+ totalOrder + ", totalPay=" + totalPay + ", totalWithdraw=" + totalWithdraw + "]";
	}
}
