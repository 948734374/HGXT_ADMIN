package com.mcfish.entity.common;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_system_smtp")
public class TbSystemSmtp {
    @Id
    private Integer id;

    /**
     * 企业码
     */
    private String corpid;

    /**
     * 邮箱地址
     */
    private String address;

    /**
     * smtp服务器地址
     */
    @Column(name = "host_name")
    private String hostName;

    /**
     * smtp端口
     */
    @Column(name = "host_port")
    private String hostPort;

    /**
     * 登陆账号
     */
    @Column(name = "auth_user")
    private String authUser;

    /**
     * 登录密码
     */
    @Column(name = "auth_pwd")
    private String authPwd;

    /**
     * 0-有效 1-无效
     */
    private Byte status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取企业码
     *
     * @return corpid - 企业码
     */
    public String getCorpid() {
        return corpid;
    }

    /**
     * 设置企业码
     *
     * @param corpid 企业码
     */
    public void setCorpid(String corpid) {
        this.corpid = corpid;
    }

    /**
     * 获取邮箱地址
     *
     * @return address - 邮箱地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置邮箱地址
     *
     * @param address 邮箱地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取smtp服务器地址
     *
     * @return host_name - smtp服务器地址
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * 设置smtp服务器地址
     *
     * @param hostName smtp服务器地址
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    /**
     * 获取smtp端口
     *
     * @return host_port - smtp端口
     */
    public String getHostPort() {
        return hostPort;
    }

    /**
     * 设置smtp端口
     *
     * @param hostPort smtp端口
     */
    public void setHostPort(String hostPort) {
        this.hostPort = hostPort;
    }

    /**
     * 获取登陆账号
     *
     * @return auth_user - 登陆账号
     */
    public String getAuthUser() {
        return authUser;
    }

    /**
     * 设置登陆账号
     *
     * @param authUser 登陆账号
     */
    public void setAuthUser(String authUser) {
        this.authUser = authUser;
    }

    /**
     * 获取登录密码
     *
     * @return auth_pwd - 登录密码
     */
    public String getAuthPwd() {
        return authPwd;
    }

    /**
     * 设置登录密码
     *
     * @param authPwd 登录密码
     */
    public void setAuthPwd(String authPwd) {
        this.authPwd = authPwd;
    }

    /**
     * 获取0-有效 1-无效
     *
     * @return status - 0-有效 1-无效
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置0-有效 1-无效
     *
     * @param status 0-有效 1-无效
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}