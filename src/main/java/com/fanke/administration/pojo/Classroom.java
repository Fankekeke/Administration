package com.fanke.administration.pojo;

/**
 * 教室表
 */
public class Classroom {
    //教室id
    private Integer roomId;
    //教室名称
    private String roomName;
    //是否有投影机
    private Integer projection;
    //座位数
    private Integer seatNum;
    //保留删除
    private Integer ups;

    public Integer getUps() {
        return ups;
    }

    public void setUps(Integer ups) {
        this.ups = ups;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getProjection() {
        return projection;
    }

    public void setProjection(Integer projection) {
        this.projection = projection;
    }

    public Integer getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(Integer seatNum) {
        this.seatNum = seatNum;
    }

    /**
     * 有参构造
     * @param roomName
     * @param projection
     * @param seatNum
     */
    public Classroom(String roomName, Integer projection, Integer seatNum) {
        this.roomName = roomName;
        this.projection = projection;
        this.seatNum = seatNum;
    }

    /**
     * 无参构造
     */
    public Classroom(){}

    @Override
    public String toString() {
        return "Classroom{" +
                "roomId=" + roomId +
                ", roomName='" + roomName + '\'' +
                ", projection=" + projection +
                ", seatNum=" + seatNum +
                '}';
    }
}
