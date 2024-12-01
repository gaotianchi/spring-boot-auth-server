package com.gaotianchi.auth.service.impl;

import com.gaotianchi.auth.dao.PermissionDao;
import com.gaotianchi.auth.entity.Permission;
import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.exception.SQLException;
import com.gaotianchi.auth.service.PermissionService;
import org.springframework.stereotype.Service;


/**
 * 权限表(Permission)表服务实现类
 *
 * @author gaotianchi
 * @since 2024-11-28 20:45:39
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    private final PermissionDao permissionDao;

    public PermissionServiceImpl(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    @Override
    public void insert(Permission permission) {
        if (permissionDao.insert(permission) != 1) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void deleteById(Integer id) {
        if (permissionDao.deleteById(id) != 1) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void update(Permission permission) {
        if (permissionDao.update(permission) != 1) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public Permission findById(Integer id) {
        return permissionDao.selectById(id);
    }
}
