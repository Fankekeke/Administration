package com.fanke.administration.service.impl;

import com.fanke.administration.mapper.TeacherMapper;
import com.fanke.administration.pojo.Teacher;
import com.fanke.administration.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Transactional
@Service("teacherServiceImpl")
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    @Qualifier("teacherMapper")
    private TeacherMapper teacherMapper;

    @Override
    public int insertTeacher(Teacher teacher) {
        return this.teacherMapper.insertTeacher(teacher);
    }

    @Override
    public int updateTeacher(Teacher teacher) {
        return this.teacherMapper.updateTeacher(teacher);
    }

    @Override
    public Teacher selectTeacherById(Integer teaId) {
        return this.teacherMapper.selectTeacherById(teaId);
    }

    @Override
    public List<Teacher> selectTeacher(Integer pageNo, Integer pageSize, String teaName) {
        return this.teacherMapper.selectTeacher(pageNo,pageSize,teaName);
    }

    @Override
    public int selectTeaCount() {
        return this.teacherMapper.selectTeaCount();
    }

    @Override
    public List<Teacher> selectAll() {
        return this.teacherMapper.selectAll();
    }
}
