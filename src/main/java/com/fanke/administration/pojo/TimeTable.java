package com.fanke.administration.pojo;


import java.util.Date;

/**
 * 课表
 */
public class TimeTable {
    //课表id
    private Integer tabId;
    //教室id
    private Integer roomId;
    //教员id
    private Integer teaId;
    //班级id
    private Integer clasId;
    //课程id
    private Integer curId;
    //课表时间
    private Date tabTime;
    //上午or下午
    private String morning;
    //星期
    private String xingqi;

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getTeaId() {
        return teaId;
    }

    public void setTeaId(Integer teaId) {
        this.teaId = teaId;
    }

    public Integer getClasId() {
        return clasId;
    }

    public void setClasId(Integer clasId) {
        this.clasId = clasId;
    }

    public Integer getCurId() {
        return curId;
    }

    public void setCurId(Integer curId) {
        this.curId = curId;
    }

    public String getXingqi() {
        return xingqi;
    }

    public void setXingqi(String xingqi) {
        this.xingqi = xingqi;
    }

    private Classes classes;
    private Classroom classroom;
    private Curriculum curriculum;
    private Teacher teacher;

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Integer getTabId() {
        return tabId;
    }

    public void setTabId(Integer tabId) {
        this.tabId = tabId;
    }

    /*public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getTeaId() {
        return teaId;
    }

    public void setTeaId(Integer teaId) {
        this.teaId = teaId;
    }

    public Integer getClasId() {
        return clasId;
    }

    public void setClasId(Integer clasId) {
        this.clasId = clasId;
    }

    public Integer getCurId() {
        return curId;
    }

    public void setCurId(Integer curId) {
        this.curId = curId;
    }*/

    public Date getTabTime() {
        return tabTime;
    }

    public void setTabTime(Date tabTime) {
        this.tabTime = tabTime;
    }

    public String getMorning() {
        return morning;
    }

    public void setMorning(String morning) {
        this.morning = morning;
    }

    /**
     * 有参构造
     * @param roomId
     * @param teaId
     * @param clasId
     * @param curId
     * @param tabTime
     * @param morning
     */
    public TimeTable(Integer roomId, Integer teaId, Integer clasId, Integer curId, Date tabTime, String morning) {
       /* this.roomId = roomId;
        this.teaId = teaId;
        this.clasId = clasId;
        this.curId = curId;*/
        this.tabTime = tabTime;
        this.morning = morning;
    }

    /**
     * 无参构造
     */
    public TimeTable(){}

    @Override
    public String toString() {
        return "TimeTable{" +
                "tabId=" + tabId +/*
                ", roomId=" + roomId +
                ", teaId=" + teaId +
                ", clasId=" + clasId +
                ", curId=" + curId +*/
                ", tabTime=" + tabTime +
                ", morning='" + morning + '\'' +
                '}';
    }
}
