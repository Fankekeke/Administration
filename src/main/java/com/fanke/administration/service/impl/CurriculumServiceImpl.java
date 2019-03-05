package com.fanke.administration.service.impl;

import com.fanke.administration.mapper.CurriculumMapper;
import com.fanke.administration.pojo.Curriculum;
import com.fanke.administration.service.CurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("curriculumServiceImpl")
@Transactional
public class CurriculumServiceImpl implements CurriculumService {

    @Autowired
    @Qualifier("curriculumMapper")
    private CurriculumMapper curriculumMapper;

    @Override
    public int insertCur(Curriculum curriculum) {
        return this.curriculumMapper.insertCur(curriculum);
    }

    @Override
    public int updateCur(Curriculum curriculum) {
        return this.curriculumMapper.updateCur(curriculum);
    }

    @Override
    public Curriculum selectCurById(Integer curId) {
        return this.curriculumMapper.selectCurById(curId);
    }

    @Override
    public List<Curriculum> selectCur(Integer pageNo, Integer pageSize, String stage, String curName) {
        return this.curriculumMapper.selectCur(pageNo,pageSize,stage,curName);
    }

    @Override
    public int selectCurCount() {
        return this.curriculumMapper.selectCurCount();
    }

    @Override
    public List<Curriculum> selectAll() {
        return this.curriculumMapper.selectAll();
    }
}
