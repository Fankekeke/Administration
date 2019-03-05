package com.fanke.administration.service;

import com.fanke.administration.pojo.Journal;

import java.util.List;

public interface JournalService {
    /**
     * 添加日志
     * @param journal
     * @return
     */
    int insertJou(Journal journal);

    /**
     * 查询所有日志
     * @return
     */
    List<Journal> selectAll();

    /**
     * 查询总数量
     * @return
     */
    int selectCount();
}
