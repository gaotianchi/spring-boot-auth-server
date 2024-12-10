package com.gaotianchi.auth.base.service;

import com.gaotianchi.auth.base.entity.ActionLog;
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

    void addNewActionLogsInBatches(List<ActionLog> actionLogs);

    void removeActionLogById(Integer id);

    void removeActionLogsInBatchesByIds(List<Integer> ids);

    void updateActionLogById(ActionLog actionLog);

    void addNewOrUpdateExistingActionLogsInBatches(List<ActionLog> actionLogs);

    ActionLog getActionLogById(Integer id);

    Page<ActionLog> getActionLogsByPage(ActionLog actionLog, PageRequest pageRequest);
}
