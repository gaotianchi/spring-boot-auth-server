package com.gaotianchi.auth.base.dao;

import com.gaotianchi.auth.base.entity.ActionLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 用户行为日志(ActionLog)表数据库访问层
 *
 * @author gaotianchi
 * @since 2024-12-07 11:24:27
 */
@Mapper
public interface ActionLogBaseDao {

    int insertActionLog(ActionLog actionLog);

    int insertActionLogsInBatches(@Param("entities") List<ActionLog> entities);

    int deleteActionLogById(Integer id);

    int deleteActionLogsInBatchesByIds(@Param("ids") List<Integer> ids);

    int updateActionLogById(ActionLog actionLog);

    int insertOrUpdateExistingActionLogsInBatches(@Param("entities") List<ActionLog> entities);

    ActionLog selectActionLogById(Integer id);

    long countByActionLog(ActionLog actionLog);

    List<ActionLog> selectActionLogsByPage(ActionLog actionLog, @Param("pageable") Pageable pageable);
}

