package com.gaotianchi.auth.service.impl;

import com.gaotianchi.auth.dao.ActionLogDao;
import com.gaotianchi.auth.entity.ActionLog;
import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.exception.SQLException;
import com.gaotianchi.auth.service.ActionLogService;
import org.springframework.stereotype.Service;


/**
 * 用户行为日志(ActionLog)表服务实现类
 *
 * @author gaotianchi
 * @since 2024-11-28 20:45:39
 */
@Service("actionLogService")
public class ActionLogServiceImpl implements ActionLogService {

    private final ActionLogDao actionLogDao;

    public ActionLogServiceImpl(ActionLogDao actionLogDao) {
        this.actionLogDao = actionLogDao;
    }

    @Override
    public void insert(ActionLog actionLog) {
        if (actionLogDao.insert(actionLog) != 1) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void deleteById(Integer id) {
        if (actionLogDao.deleteById(id) != 1) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void update(ActionLog actionLog) {
        if (actionLogDao.update(actionLog) != 1) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public ActionLog findById(Integer id) {
        return actionLogDao.selectById(id);
    }
}
