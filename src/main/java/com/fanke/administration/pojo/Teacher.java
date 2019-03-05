package com.fanke.administration.pojo;

/**
 * 教员表
 */
public class Teacher {
    //教员id
    private Integer teaId;
    //教员姓名
    private String teaName;
    //教员邮箱
    private String teaMail;
    //保留删除
    private Integer ups;

    public Integer getUps() {
        return ups;
    }

    public void setUps(Integer ups) {
        this.ups = ups;
    }

    public Integer getTeaId() {
        return teaId;
    }

    public void setTeaId(Integer teaId) {
        this.teaId = teaId;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public String getTeaMail() {
        return teaMail;
    }

    public void setTeaMail(String teaMail) {
        this.teaMail = teaMail;
    }

    /**
     * 有参构造
     * @param teaName
     * @param teaMail
     */
    public Teacher(String teaName, String teaMail) {
        this.teaName = teaName;
        this.teaMail = teaMail;
    }

    /**
     * 无参构造
     */
    public Teacher(){}

    @Override
    public String toString() {
        return "Teacher{" +
                "teaId=" + teaId +
                ", teaName='" + teaName + '\'' +
                ", teaMail='" + teaMail + '\'' +
                '}';
    }
}
