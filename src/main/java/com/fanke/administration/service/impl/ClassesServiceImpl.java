package com.fanke.administration.service.impl;

import com.fanke.administration.mapper.ClassesMapper;
import com.fanke.administration.pojo.Classes;
import com.fanke.administration.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("classesServiceImpl")
@Transactional
public class ClassesServiceImpl implements ClassesService {

    @Autowired
    @Qualifier("classesMapper")
    private ClassesMapper classesMapper;

    @Override
    public int insertCla(Classes classes) {
        return this.classesMapper.insertCla(classes);
    }

    @Override
    public int updateCla(Classes classes) {
        return this.classesMapper.updateCla(classes);
    }

    @Override
    public List<Classes> selectCla(Integer pageNo, Integer pageSize, String clasName, String stage) {
        return this.classesMapper.selectCla(pageNo,pageSize,clasName,stage);
    }

    @Override
    public Classes selectClaById(Integer clasId) {
        return this.classesMapper.selectClaById(clasId);
    }

    @Override
    public int selectClaCount() {
        return this.classesMapper.selectClaCount();
    }

    @Override
    public List<Classes> selectAll() {
        return this.classesMapper.selectAll();
    }
}
