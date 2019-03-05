package com.fanke.administration.service.impl;

import com.fanke.administration.mapper.JournalMapper;
import com.fanke.administration.pojo.Journal;
import com.fanke.administration.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("journalServiceImpl")
@Transactional
public class JournalServiceImpl implements JournalService {

    @Autowired
    @Qualifier("journalMapper")
    private JournalMapper journalMapper;

    @Override
    public int insertJou(Journal journal) {
        return this.journalMapper.insertJou(journal);
    }

    @Override
    public List<Journal> selectAll() {
        return this.journalMapper.selectAll();
    }

    @Override
    public int selectCount() {
        return this.journalMapper.selectCount();
    }
}
