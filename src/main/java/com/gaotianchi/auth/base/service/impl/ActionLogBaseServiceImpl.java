package com.gaotianchi.auth.base.service.impl;

import com.gaotianchi.auth.base.dao.ActionLogBaseDao;
import com.gaotianchi.auth.base.entity.ActionLog;
import com.gaotianchi.auth.base.service.ActionLogBaseService;
import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.exception.SQLException;
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
    public void addNewActionLogsInBatches(List<ActionLog> actionLogs) {
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
    public void removeActionLogsInBatchesByIds(List<Integer> ids) {
        if (actionLogBaseDao.deleteActionLogsInBatchesByIds(ids) != ids.size()) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void updateActionLogById(ActionLog actionLog) {
        if (actionLogBaseDao.updateActionLogById(actionLog) != 1) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public void addNewOrUpdateExistingActionLogsInBatches(List<ActionLog> actionLogs) {
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
