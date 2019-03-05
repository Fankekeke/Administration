package com.fanke.administration.pojo;

/**
 * 班级表
 */
public class Classes {
    //班级id
    private Integer clasId;
    //班级名称
    private String clasName;
    //班级人数
    private Integer clasNum;
    //当前阶段
    private String stage;
    //保留删除
    private Integer ups;

    public Integer getUps() {
        return ups;
    }

    public void setUps(Integer ups) {
        this.ups = ups;
    }

    public Integer getClasId() {
        return clasId;
    }

    public void setClasId(Integer clasId) {
        this.clasId = clasId;
    }

    public String getClasName() {
        return clasName;
    }

    public void setClasName(String clasName) {
        this.clasName = clasName;
    }

    public Integer getClasNum() {
        return clasNum;
    }

    public void setClasNum(Integer clasNum) {
        this.clasNum = clasNum;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    /**
     * 有参构造
     * @param clasName
     * @param clasNum
     * @param stage
     */
    public Classes(String clasName, Integer clasNum, String stage) {
        this.clasName = clasName;
        this.clasNum = clasNum;
        this.stage = stage;
    }

    /**
     * 无参构造
     */
    public Classes(){}

    @Override
    public String toString() {
        return "Classes{" +
                "clasId=" + clasId +
                ", clasName='" + clasName + '\'' +
                ", clasNum=" + clasNum +
                ", stage='" + stage + '\'' +
                ", ups=" + ups +
                '}';
    }
}
