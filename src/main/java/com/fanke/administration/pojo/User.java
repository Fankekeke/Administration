package com.fanke.administration.pojo;

import java.util.Date;

/**
 * 用户表
 */
public class User {
    //用户id
    private Integer userId;
    //用户名称
    private String userName;
    //用户密码
    private String userPwd;
    //创建时间
    private Date userTime;
    //用户邮箱
    private String userMail;

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Date getUserTime() {
        return userTime;
    }

    public void setUserTime(Date userTime) {
        this.userTime = userTime;
    }

    /**
     * 有参构造
     * @param userName
     * @param userPwd
     * @param userTime
     */
    public User(String userName, String userPwd, Date userTime,String userMail) {
        this.userName = userName;
        this.userPwd = userPwd;
        this.userTime = userTime;
        this.userMail=userMail;
    }

    /**
     * 无参构造
     */
    public User(){}

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userTime=" + userTime +
                ", userMail='" + userMail + '\'' +
                '}';
    }
}
