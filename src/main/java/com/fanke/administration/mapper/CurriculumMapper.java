package com.fanke.administration.mapper;

import com.fanke.administration.pojo.Curriculum;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurriculumMapper {
    /**
     * 添加课程
     * @param curriculum
     * @return
     */
    int insertCur(Curriculum curriculum);

    /**
     * 修改课程
     * @param curriculum
     * @return
     */
    int updateCur(Curriculum curriculum);

    /**
     * 根据id获取课程
     * @param curId
     * @return
     */
    Curriculum selectCurById(@Param("curId") Integer curId);

    /**
     * 分页查询
     * @param pageNo
     * @param pageSize
     * @param stage
     * @param curName
     * @return
     */
    List<Curriculum> selectCur(@Param("pageNo") Integer pageNo,@Param("pageSize") Integer pageSize,@Param("stage") String stage,@Param("curName") String curName);

    /**
     * 查询总数量
     * @return
     */
    int selectCurCount();

    /**
     *查询全部
     * @return
     */
    List<Curriculum> selectAll();
}
