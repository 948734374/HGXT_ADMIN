package com.mcfish.entity.common;

import java.io.Serializable;
import java.util.Date;


/**
 * banner实体
 * @author ZhouXiaobing
 * @date 2018年3月29日 下午3:36:43 
 * @version 1.0
 */
public class Banner implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4097036509915059059L;
	
	
	private int id;
	private String title;		//BANNER标题
	private String image;		//BANNER图片
	private Integer pos;		//BANNER的位置
	private String url;			//点击跳转URL
	private String data;		//扩展字段：其他跳转方式，自定义
	private Date begin;			//开始时间
	private Date end;			//结束时间
	private Integer status;		//状态：0-在线 1-下线
	private Date create_time;	//创建时间
	private Long total;			//统计数量
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Integer getPos() {
		return pos;
	}
	public void setPos(Integer pos) {
		this.pos = pos;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
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
		return "Banner [id=" + id + ", title=" + title + ", image=" + image + ", pos=" + pos + ", url=" + url
				+ ", data=" + data + ", begin=" + begin + ", end=" + end + ", status=" + status + ", create_time="
				+ create_time + ", total=" + total + "]";
	}
}
