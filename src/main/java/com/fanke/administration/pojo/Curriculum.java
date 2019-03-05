package com.fanke.administration.pojo;

/**
 * 课程表
 */
public class Curriculum {
    //课程id
    private Integer curId;
    //课程名称
    private String curName;
    //当前阶段
    private String stage;
    //章节名称
    private String chapter;
    //课时
    private Integer curTime;
    //保留删除
    private Integer ups;

    public Integer getUps() {
        return ups;
    }

    public void setUps(Integer ups) {
        this.ups = ups;
    }

    public Integer getCurId() {
        return curId;
    }

    public void setCurId(Integer curId) {
        this.curId = curId;
    }

    public String getCurName() {
        return curName;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public Integer getCurTime() {
        return curTime;
    }

    public void setCurTime(Integer curTime) {
        this.curTime = curTime;
    }

    /**
     * 有参构造
     * @param curName
     * @param stage
     * @param chapter
     * @param curTime
     */
    public Curriculum(String curName, String stage, String chapter, Integer curTime) {
        this.curName = curName;
        this.stage = stage;
        this.chapter = chapter;
        this.curTime = curTime;
    }

    /**
     * 无参构造
     */
    public Curriculum(){}

    @Override
    public String toString() {
        return "Curriculum{" +
                "curId=" + curId +
                ", curName='" + curName + '\'' +
                ", stage='" + stage + '\'' +
                ", chapter='" + chapter + '\'' +
                ", curTime=" + curTime +
                '}';
    }
}
