package com.fanke.administration.service;

import com.fanke.administration.pojo.TimeTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TimeTableService {
    /**
     * 添加课表
     * @param timeTable
     * @return
     */
    int insertTable(TimeTable timeTable);

    /**
     * 修改课表
     * @param timeTable
     * @return
     */
    int updateTable(TimeTable timeTable);

    /**
     * 根据id获取课表信息
     * @param tabId
     * @return
     */
    TimeTable selectTableById(@Param("tabId") Integer tabId);

    /**
     * 分页查询
     * @param pageNo
     * @param pageSize
     * @param teaId 教员id
     * @param clasId 班级id
     * @param curId 课程id
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    List<TimeTable> selectTable(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("teaId") Integer teaId, @Param("clasId") Integer clasId, @Param("curId") Integer curId, @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 查询总数量
     * @return
     */
    int selectTableCount();

    /**
     * 查询全部
     * @return
     */
    List<TimeTable> selectAll();
}
