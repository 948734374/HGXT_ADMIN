package com.mcfish.entity.common;

import java.io.Serializable;
import java.util.Date;


/**
 * 消息推送实体类
 * @author ZhouXiaobing
 * @date 2018年3月29日 上午8:15:02 
 * @version 1.0
 */
public class Message implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -3363627144672979677L;
	
	
	/**
	 * tb_system_message
	 */
	private int id;				//主键
	private int from;			//消息来源: 0:系统消息 >10000：用户消息
	private String title;		//消息标题
	private String image;		//图片
	private String content;		//消息内容
	private int status;			//消息状态：0-未发送 1-已发送
	private Date create_time;	//发送消息时间
	
	/**
	 *  tb_user_message
	 */
	private int rid;			//表主键
	private int user_id;		//所属用户
	private int msg_id;			//消息id
	private int ext;			//消息附件信息: 完全自定义
	private int rstatus;		//状态 0-未读 1-已读
	private Date rcreate_time;	//创建时间
	
	private Long total;			//统计数量
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(int msg_id) {
		this.msg_id = msg_id;
	}
	public int getExt() {
		return ext;
	}
	public void setExt(int ext) {
		this.ext = ext;
	}
	public int getRstatus() {
		return rstatus;
	}
	public void setRstatus(int rstatus) {
		this.rstatus = rstatus;
	}
	public Date getRcreate_time() {
		return rcreate_time;
	}
	public void setRcreate_time(Date rcreate_time) {
		this.rcreate_time = rcreate_time;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", from=" + from + ", title=" + title + ", image=" + image + ", content=" + content
				+ ", status=" + status + ", create_time=" + create_time + ", rid=" + rid + ", user_id=" + user_id
				+ ", msg_id=" + msg_id + ", ext=" + ext + ", rstatus=" + rstatus + ", rcreate_time=" + rcreate_time
				+ ", total=" + total + "]";
	}
}
