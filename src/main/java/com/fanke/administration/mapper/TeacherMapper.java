package com.fanke.administration.mapper;

import com.fanke.administration.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper {
    /**
     * 添加教师
     * @param teacher
     * @return
     */
    int insertTeacher(Teacher teacher);

    /**
     * 修改教师信息
     * @param teacher
     * @return
     */
    int updateTeacher(Teacher teacher);

    /**
     * 根据id获取教员信息
     * @param teaId
     * @return
     */
    Teacher selectTeacherById(@Param("teaId") Integer teaId);

    /**
     * 分页查询
     * @param pageNo
     * @param pageSize
     * @param teaName
     * @return
     */
    List<Teacher> selectTeacher(@Param("pageNo") Integer pageNo,@Param("pageSize") Integer pageSize,@Param("teaName") String teaName);

    /**
     * 查询总数量
     * @return
     */
    int selectTeaCount();

    /**
     * 查询全部
     * @return
     */
    List<Teacher> selectAll();
}
