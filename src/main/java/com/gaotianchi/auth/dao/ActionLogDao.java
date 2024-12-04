package com.gaotianchi.auth.dao;

import com.gaotianchi.auth.entity.ActionLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户行为日志(ActionLog)表数据库访问层
 *
 * @author gaotianchi
 * @since 2024-11-27 21:35:14
 */
@Mapper
public interface ActionLogDao {

    int insert(ActionLog actionLog);

    int deleteById(Integer id);

    int update(ActionLog actionLog);

    ActionLog selectById(Integer id);
}

