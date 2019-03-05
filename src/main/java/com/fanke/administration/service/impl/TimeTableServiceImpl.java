package com.fanke.administration.service.impl;

import com.fanke.administration.mapper.TimeTableMapper;
import com.fanke.administration.pojo.TimeTable;
import com.fanke.administration.service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("timeTableServiceImpl")
@Transactional
public class TimeTableServiceImpl implements TimeTableService {

    @Autowired
    @Qualifier("timeTableMapper")
    private TimeTableMapper timeTableMapper;

    @Override
    public int insertTable(TimeTable timeTable) {
        return this.timeTableMapper.insertTable(timeTable);
    }

    @Override
    public int updateTable(TimeTable timeTable) {
        return this.timeTableMapper.updateTable(timeTable);
    }

    @Override
    public TimeTable selectTableById(Integer tabId) {
        return this.timeTableMapper.selectTableById(tabId);
    }

    @Override
    public List<TimeTable> selectTable(Integer pageNo, Integer pageSize, Integer teaId, Integer clasId, Integer curId, String startTime, String endTime) {
        return this.timeTableMapper.selectTable(pageNo,pageSize,teaId,clasId,curId,startTime,endTime);
    }

    @Override
    public int selectTableCount() {
        return this.timeTableMapper.selectTableCount();
    }

    @Override
    public List<TimeTable> selectAll() {
        return this.timeTableMapper.selectAll();
    }
}
