package com.gaotianchi.auth.base.service.impl;

import com.gaotianchi.auth.base.dao.RolePermissionBaseDao;
import com.gaotianchi.auth.base.entity.RolePermission;
import com.gaotianchi.auth.base.service.RolePermissionBaseService;
import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.exception.SQLException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 角色权限表(RolePermission)表服务实现类
 *
 * @author gaotianchi
 * @since 2024-12-07 11:29:50
 */
@Service("rolePermissionBaseService")
public class RolePermissionBaseServiceImpl implements RolePermissionBaseService {

    private final RolePermissionBaseDao rolePermissionBaseDao;

    public RolePermissionBaseServiceImpl(RolePermissionBaseDao rolePermissionBaseDao) {
        Assert.notNull(rolePermissionBaseDao, "RolePermissionRepository cannot be null");
        this.rolePermissionBaseDao = rolePermissionBaseDao;
    }

    @Override
    public void addNewRolePermission(RolePermission rolePermission) {
        if (rolePermissionBaseDao.insertRolePermission(rolePermission) != 1) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void addNewRolePermissionsInBatches(List<RolePermission> rolePermissions) {
        if (rolePermissionBaseDao.insertRolePermissionsInBatches(rolePermissions) != rolePermissions.size()) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void removeRolePermissionById(Integer id) {
        if (rolePermissionBaseDao.deleteRolePermissionById(id) != 1) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void removeRolePermissionsInBatchesByIds(List<Integer> ids) {
        if (rolePermissionBaseDao.deleteRolePermissionsInBatchesByIds(ids) != ids.size()) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void updateRolePermissionById(RolePermission rolePermission) {
        if (rolePermissionBaseDao.updateRolePermissionById(rolePermission) != 1) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public void addNewOrUpdateExistingRolePermissionsInBatches(List<RolePermission> rolePermissions) {
        if (rolePermissionBaseDao.insertOrUpdateExistingRolePermissionsInBatches(rolePermissions) != rolePermissions.size()) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public RolePermission getRolePermissionById(Integer id) {
        return rolePermissionBaseDao.selectRolePermissionById(id);
    }

    @Override
    public Page<RolePermission> getRolePermissionsByPage(RolePermission rolePermission, PageRequest pageRequest) {
        long total = rolePermissionBaseDao.countByRolePermission(rolePermission);
        return new PageImpl<>(rolePermissionBaseDao.selectRolePermissionsByPage(rolePermission, pageRequest), pageRequest, total);
    }


}
