package com.gaotianchi.auth.repository.service.impl;

import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.exception.SQLException;
import com.gaotianchi.auth.repository.dao.RolePermissionRepositoryDao;
import com.gaotianchi.auth.repository.entity.RolePermission;
import com.gaotianchi.auth.repository.service.RolePermissionRepositoryService;
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
 * @since 2024-12-06 19:30:49
 */
@Service("rolePermissionService")
public class RolePermissionRepositoryServiceImpl implements RolePermissionRepositoryService {

    private final RolePermissionRepositoryDao rolePermissionRepositoryDao;

    public RolePermissionRepositoryServiceImpl(RolePermissionRepositoryDao rolePermissionRepositoryDao) {
        Assert.notNull(rolePermissionRepositoryDao, "RolePermissionRepository cannot be null");
        this.rolePermissionRepositoryDao = rolePermissionRepositoryDao;
    }

    @Override
    public void addNewRolePermission(RolePermission rolePermission) {
        if (rolePermissionRepositoryDao.insertRolePermission(rolePermission) != 1) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void addNewRolePermissionsBatch(List<RolePermission> rolePermissions) {
        if (rolePermissionRepositoryDao.insertRolePermissionsBatch(rolePermissions) != rolePermissions.size()) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void removeRolePermissionById(Integer id) {
        if (rolePermissionRepositoryDao.deleteRolePermissionById(id) != 1) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void removeRolePermissionsBatchByIds(List<Integer> ids) {
        if (rolePermissionRepositoryDao.deleteRolePermissionsBatchByIds(ids) != ids.size()) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void updateRolePermissionDetailsById(RolePermission rolePermission) {
        if (rolePermissionRepositoryDao.updateRolePermissionById(rolePermission) != 1) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public void addNewOrUpdateRolePermissionsBatch(List<RolePermission> rolePermissions) {
        if (rolePermissionRepositoryDao.insertOrUpdateRolePermissionsBatch(rolePermissions) != rolePermissions.size()) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public RolePermission getRolePermissionById(Integer id) {
        return rolePermissionRepositoryDao.selectRolePermissionById(id);
    }

    @Override
    public Page<RolePermission> getRolePermissionsByPage(RolePermission rolePermission, PageRequest pageRequest) {
        long total = rolePermissionRepositoryDao.selectRolePermissionsCount(rolePermission);
        return new PageImpl<>(rolePermissionRepositoryDao.selectRolePermissionsByPage(rolePermission, pageRequest), pageRequest, total);
    }


}
