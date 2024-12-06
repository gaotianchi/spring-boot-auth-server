package com.gaotianchi.auth.repository.dao;

import com.gaotianchi.auth.repository.entity.ActionLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 用户行为日志(ActionLog)表数据库访问层
 *
 * @author gaotianchi
 * @since 2024-12-06 19:28:40
 */
@Mapper
public interface ActionLogRepositoryDao {

    int insertActionLog(ActionLog actionLog);

    int insertActionLogsBatch(@Param("entities") List<ActionLog> entities);

    int deleteActionLogById(Integer id);

    int deleteActionLogsBatchByIds(@Param("ids") List<Integer> ids);

    int updateActionLogById(ActionLog actionLog);

    int insertOrUpdateActionLogsBatch(@Param("entities") List<ActionLog> entities);

    ActionLog selectActionLogById(Integer id);

    long selectActionLogsCount(ActionLog actionLog);

    List<ActionLog> selectActionLogsByPage(ActionLog actionLog, @Param("pageable") Pageable pageable);
}

