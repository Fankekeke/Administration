package com.fanke.administration.mapper;

import com.fanke.administration.pojo.Journal;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 日志表
 */
@Repository
public interface JournalMapper {
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
