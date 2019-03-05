package com.fanke.administration.service.impl;

import com.fanke.administration.mapper.ClassroomMapper;
import com.fanke.administration.pojo.Classroom;
import com.fanke.administration.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("classroomServiceImpl")
@Transactional
public class ClassroomServiceImpl implements ClassroomService {
    @Autowired
    @Qualifier("classroomMapper")
    private ClassroomMapper classroomMapper;

    @Override
    public int insertRoom(Classroom classroom) {
        return this.classroomMapper.insertRoom(classroom);
    }

    @Override
    public int updateRoom(Classroom classroom) {
        return this.classroomMapper.updateRoom(classroom);
    }

    @Override
    public Classroom selectRoomById(Integer roomId) {
        return this.classroomMapper.selectRoomById(roomId);
    }

    @Override
    public List<Classroom> selectRoom(Integer pageNo, Integer pageSize, Integer projection, String roomName) {
        return this.classroomMapper.selectRoom(pageNo,pageSize,projection,roomName);
    }

    @Override
    public int selectRoomCount() {
        return this.classroomMapper.selectRoomCount();
    }

    @Override
    public List<Classroom> selectAll() {
        return this.classroomMapper.selectAll();
    }
}
