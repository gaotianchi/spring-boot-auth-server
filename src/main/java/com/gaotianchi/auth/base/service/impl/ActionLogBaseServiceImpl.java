package com.gaotianchi.auth.base.service.impl;

import com.gaotianchi.auth.base.dao.ActionLogBaseDao;
import com.gaotianchi.auth.base.entity.ActionLog;
import com.gaotianchi.auth.base.service.ActionLogBaseService;
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
        actionLogBaseDao.insertActionLog(actionLog);
    }

    @Override
    public void addNewActionLogsInBatches(List<ActionLog> actionLogs) {
        actionLogBaseDao.insertActionLogsInBatches(actionLogs);
    }

    @Override
    public void removeActionLogById(Integer id) {
        actionLogBaseDao.deleteActionLogById(id);
    }

    @Override
    public void removeActionLogsInBatchesByIds(List<Integer> ids) {
        actionLogBaseDao.deleteActionLogsInBatchesByIds(ids);
    }

    @Override
    public void updateActionLogById(ActionLog actionLog) {
        actionLogBaseDao.updateActionLogById(actionLog);
    }

    @Override
    public void addNewOrUpdateExistingActionLogsInBatches(List<ActionLog> actionLogs) {
        actionLogBaseDao.insertOrUpdateExistingActionLogsInBatches(actionLogs);
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
