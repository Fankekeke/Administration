package com.fanke.administration.mapper;

import com.fanke.administration.pojo.Classroom;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomMapper {
    /**
     * 添加教室
     * @param classroom
     * @return
     */
    int insertRoom(Classroom classroom);

    /**
     * 修改教室
     * @param classroom
     * @return
     */
    int updateRoom(Classroom classroom);

    /**
     * 根据id获取教室信息
     * @param roomId
     * @return
     */
    Classroom selectRoomById(@Param("roomId") Integer roomId);

    /**
     * 分页查询
     * @param pageNo
     * @param pageSize
     * @param projection
     * @param roomName
     * @return
     */
    List<Classroom> selectRoom(@Param("pageNo") Integer pageNo,@Param("pageSize") Integer pageSize,@Param("projection") Integer projection,@Param("roomName") String roomName);

    /**
     * 查询总数量
     * @return
     */
    int selectRoomCount();

    /**
     * 查询全部数据
     * @return
     */
    List<Classroom> selectAll();

}
