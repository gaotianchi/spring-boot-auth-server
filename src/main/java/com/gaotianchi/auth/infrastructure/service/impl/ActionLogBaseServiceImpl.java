package com.gaotianchi.auth.infrastructure.service.impl;

import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.exception.SQLException;
import com.gaotianchi.auth.infrastructure.dao.ActionLogBaseDao;
import com.gaotianchi.auth.infrastructure.entity.ActionLog;
import com.gaotianchi.auth.infrastructure.service.ActionLogBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 用户行为日志(ActionLog)表服务实现类
 *
 * @author gaotianchi
 * @since 2024-12-07 11:29:50
 */
@Service("actionLogBaseService")
public class ActionLogBaseServiceImpl implements ActionLogBaseService {

    private final ActionLogBaseDao actionLogBaseDao;

    public ActionLogBaseServiceImpl(ActionLogBaseDao actionLogBaseDao) {
        Assert.notNull(actionLogBaseDao, "ActionLogRepository cannot be null");
        this.actionLogBaseDao = actionLogBaseDao;
    }

    @Override
    public void addNewActionLog(ActionLog actionLog) {
        if (actionLogBaseDao.insertActionLog(actionLog) != 1) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void addNewActionLogsBatch(List<ActionLog> actionLogs) {
        if (actionLogBaseDao.insertActionLogsInBatches(actionLogs) != actionLogs.size()) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void removeActionLogById(Integer id) {
        if (actionLogBaseDao.deleteActionLogById(id) != 1) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void removeActionLogsBatchByIds(List<Integer> ids) {
        if (actionLogBaseDao.deleteActionLogsInBatchesByIds(ids) != ids.size()) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void updateActionLogDetailsById(ActionLog actionLog) {
        if (actionLogBaseDao.updateActionLogById(actionLog) != 1) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public void addNewOrUpdateActionLogsBatch(List<ActionLog> actionLogs) {
        if (actionLogBaseDao.insertOrUpdateExistingActionLogsInBatches(actionLogs) != actionLogs.size()) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public ActionLog getActionLogById(Integer id) {
        return actionLogBaseDao.selectActionLogById(id);
    }

    @Override
    public Page<ActionLog> getActionLogsByPage(ActionLog actionLog, PageRequest pageRequest) {
        long total = actionLogBaseDao.countByActionLog(actionLog);
        return new PageImpl<>(actionLogBaseDao.selectActionLogsByPage(actionLog, pageRequest), pageRequest, total);
    }


}
