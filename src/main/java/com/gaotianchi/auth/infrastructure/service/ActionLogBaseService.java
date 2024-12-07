package com.gaotianchi.auth.infrastructure.service;

import com.gaotianchi.auth.infrastructure.entity.ActionLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 用户行为日志(ActionLog)表服务接口
 *
 * @author gaotianchi
 * @since 2024-12-07 11:24:27
 */
public interface ActionLogBaseService {

    void addNewActionLog(ActionLog actionLog);

    void addNewActionLogsBatch(List<ActionLog> actionLogs);

    void removeActionLogById(Integer id);

    void removeActionLogsBatchByIds(List<Integer> ids);

    void updateActionLogDetailsById(ActionLog actionLog);

    void addNewOrUpdateActionLogsBatch(List<ActionLog> actionLogs);

    ActionLog getActionLogById(Integer id);

    Page<ActionLog> getActionLogsByPage(ActionLog actionLog, PageRequest pageRequest);
}
