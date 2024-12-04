package com.gaotianchi.auth.service.impl;

import com.gaotianchi.auth.dao.RolePermissionDao;
import com.gaotianchi.auth.entity.RolePermission;
import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.exception.SQLException;
import com.gaotianchi.auth.service.RolePermissionService;
import org.springframework.stereotype.Service;


/**
 * 角色权限表(RolePermission)表服务实现类
 *
 * @author gaotianchi
 * @since 2024-11-28 20:45:39
 */
@Service("rolePermissionService")
public class RolePermissionServiceImpl implements RolePermissionService {

    private final RolePermissionDao rolePermissionDao;

    public RolePermissionServiceImpl(RolePermissionDao rolePermissionDao) {
        this.rolePermissionDao = rolePermissionDao;
    }

    @Override
    public void insert(RolePermission rolePermission) {
        if (rolePermissionDao.insert(rolePermission) != 1) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void deleteById(Integer id) {
        if (rolePermissionDao.deleteById(id) != 1) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void update(RolePermission rolePermission) {
        if (rolePermissionDao.update(rolePermission) != 1) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public RolePermission findById(Integer id) {
        return rolePermissionDao.selectById(id);
    }
}
