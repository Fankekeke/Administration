package com.fanke.administration.service;

import com.fanke.administration.pojo.Classes;
import com.fanke.administration.pojo.TimeTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassesService {
    /**
     * 增加班级
     * @param classes
     * @return
     */
    int insertCla(Classes classes);

    /**
     * 修改班级信息
     * @param classes
     * @return
     */
    int updateCla(Classes classes);

    /**
     * 分页查询
     * @param pageNo
     * @param pageSize
     * @param clasName
     * @param stage
     * @return
     */
    List<Classes> selectCla(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("clasName") String clasName, @Param("stage") String stage);

    /**
     * 根据id获取班级信息
     * @param clasId
     * @return
     */
    Classes selectClaById(@Param("clasId") Integer clasId);

    /**
     * 查询总数量
     * @return
     */
    int selectClaCount();

    /**
     * 查询全部
     * @return
     */
    List<Classes> selectAll();
}
