package com.gaotianchi.auth.repository.service.impl;

import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.exception.SQLException;
import com.gaotianchi.auth.repository.dao.PermissionRepositoryDao;
import com.gaotianchi.auth.repository.entity.Permission;
import com.gaotianchi.auth.repository.service.PermissionRepositoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 权限表(Permission)表服务实现类
 *
 * @author gaotianchi
 * @since 2024-12-06 19:30:49
 */
@Service("permissionService")
public class PermissionRepositoryServiceImpl implements PermissionRepositoryService {

    private final PermissionRepositoryDao permissionRepositoryDao;

    public PermissionRepositoryServiceImpl(PermissionRepositoryDao permissionRepositoryDao) {
        Assert.notNull(permissionRepositoryDao, "PermissionRepository cannot be null");
        this.permissionRepositoryDao = permissionRepositoryDao;
    }

    @Override
    public void addNewPermission(Permission permission) {
        if (permissionRepositoryDao.insertPermission(permission) != 1) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void addNewPermissionsBatch(List<Permission> permissions) {
        if (permissionRepositoryDao.insertPermissionsBatch(permissions) != permissions.size()) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void removePermissionById(Integer id) {
        if (permissionRepositoryDao.deletePermissionById(id) != 1) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void removePermissionsBatchByIds(List<Integer> ids) {
        if (permissionRepositoryDao.deletePermissionsBatchByIds(ids) != ids.size()) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void updatePermissionDetailsById(Permission permission) {
        if (permissionRepositoryDao.updatePermissionById(permission) != 1) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public void addNewOrUpdatePermissionsBatch(List<Permission> permissions) {
        if (permissionRepositoryDao.insertOrUpdatePermissionsBatch(permissions) != permissions.size()) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public Permission getPermissionById(Integer id) {
        return permissionRepositoryDao.selectPermissionById(id);
    }

    @Override
    public Page<Permission> getPermissionsByPage(Permission permission, PageRequest pageRequest) {
        long total = permissionRepositoryDao.selectPermissionsCount(permission);
        return new PageImpl<>(permissionRepositoryDao.selectPermissionsByPage(permission, pageRequest), pageRequest, total);
    }


}
