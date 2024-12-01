package com.gaotianchi.auth.service.impl;

import com.gaotianchi.auth.dao.UserRoleDao;
import com.gaotianchi.auth.entity.UserRole;
import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.exception.SQLException;
import com.gaotianchi.auth.service.UserRoleService;
import org.springframework.stereotype.Service;


/**
 * (UserRole)表服务实现类
 *
 * @author gaotianchi
 * @since 2024-11-28 20:45:38
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleDao userRoleDao;

    public UserRoleServiceImpl(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    @Override
    public void insert(UserRole userRole) {
        if (userRoleDao.insert(userRole) != 1) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void deleteById(Integer id) {
        if (userRoleDao.deleteById(id) != 1) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void update(UserRole userRole) {
        if (userRoleDao.update(userRole) != 1) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public UserRole findById(Integer id) {
        return userRoleDao.selectById(id);
    }
}
