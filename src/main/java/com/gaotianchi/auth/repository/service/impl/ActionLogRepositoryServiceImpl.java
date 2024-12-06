package com.gaotianchi.auth.repository.service.impl;

import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.exception.SQLException;
import com.gaotianchi.auth.repository.dao.ActionLogRepositoryDao;
import com.gaotianchi.auth.repository.entity.ActionLog;
import com.gaotianchi.auth.repository.service.ActionLogRepositoryService;
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
 * @since 2024-12-06 19:30:49
 */
@Service("actionLogService")
public class ActionLogRepositoryServiceImpl implements ActionLogRepositoryService {

    private final ActionLogRepositoryDao actionLogRepositoryDao;

    public ActionLogRepositoryServiceImpl(ActionLogRepositoryDao actionLogRepositoryDao) {
        Assert.notNull(actionLogRepositoryDao, "ActionLogRepository cannot be null");
        this.actionLogRepositoryDao = actionLogRepositoryDao;
    }

    @Override
    public void addNewActionLog(ActionLog actionLog) {
        if (actionLogRepositoryDao.insertActionLog(actionLog) != 1) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void addNewActionLogsBatch(List<ActionLog> actionLogs) {
        if (actionLogRepositoryDao.insertActionLogsBatch(actionLogs) != actionLogs.size()) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void removeActionLogById(Integer id) {
        if (actionLogRepositoryDao.deleteActionLogById(id) != 1) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void removeActionLogsBatchByIds(List<Integer> ids) {
        if (actionLogRepositoryDao.deleteActionLogsBatchByIds(ids) != ids.size()) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void updateActionLogDetailsById(ActionLog actionLog) {
        if (actionLogRepositoryDao.updateActionLogById(actionLog) != 1) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public void addNewOrUpdateActionLogsBatch(List<ActionLog> actionLogs) {
        if (actionLogRepositoryDao.insertOrUpdateActionLogsBatch(actionLogs) != actionLogs.size()) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public ActionLog getActionLogById(Integer id) {
        return actionLogRepositoryDao.selectActionLogById(id);
    }

    @Override
    public Page<ActionLog> getActionLogsByPage(ActionLog actionLog, PageRequest pageRequest) {
        long total = actionLogRepositoryDao.selectActionLogsCount(actionLog);
        return new PageImpl<>(actionLogRepositoryDao.selectActionLogsByPage(actionLog, pageRequest), pageRequest, total);
    }


}
