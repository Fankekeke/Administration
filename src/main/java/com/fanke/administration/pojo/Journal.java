package com.fanke.administration.pojo;

import java.util.Date;

/**
 * 日志表
 */
public class Journal {
    //日志id
    private Integer jouId;
    //操作时间
    private Date jouTime;
    //操作员
    private Integer userId;
    //内容摘要
    private String content;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getJouId() {
        return jouId;
    }

    public void setJouId(Integer jouId) {
        this.jouId = jouId;
    }

    public Date getJouTime() {
        return jouTime;
    }

    public void setJouTime(Date jouTime) {
        this.jouTime = jouTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 有参构造
     * @param jouTime
     * @param userId
     * @param content
     */
    public Journal(Date jouTime, Integer userId, String content) {
        this.jouTime = jouTime;
        this.userId = userId;
        this.content = content;
    }

    /**
     * 无参构造
     */
    public Journal(){}

    @Override
    public String toString() {
        return "Journal{" +
                "jouId=" + jouId +
                ", jouTime=" + jouTime +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                '}';
    }
}
