package com.gaotianchi.auth.service;

import com.gaotianchi.auth.entity.ActionLog;

/**
 * 用户行为日志(ActionLog)表服务接口
 *
 * @author gaotianchi
 * @since 2024-11-28 20:19:44
 */
public interface ActionLogService {

    void insert(ActionLog actionLog);

    void deleteById(Integer id);

    void update(ActionLog actionLog);

    ActionLog findById(Integer id);
}
