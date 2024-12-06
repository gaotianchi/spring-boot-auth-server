package com.gaotianchi.auth.service.impl;

import com.gaotianchi.auth.dao.PermissionDao;
import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.exception.SQLException;
import com.gaotianchi.auth.repository.entity.Permission;
import com.gaotianchi.auth.service.PermissionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gaotianchi
 * @since 2024/12/5 22:00
 **/
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    private final PermissionDao permissionDao;

    public PermissionServiceImpl(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    @Override
    public void addNewPermission(Permission permission) {
        if (permissionDao.insertPermission(permission) != 1) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void addNewPermissionsBatch(List<Permission> permissions) {
        if (permissionDao.insertPermissionsBatch(permissions) != permissions.size()) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void removePermissionById(Integer id) {
        if (permissionDao.deletePermissionById(id) != 1) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void removePermissionsBatchByIds(List<Integer> ids) {
        if (permissionDao.deletePermissionsBatchByIds(ids) != ids.size()) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void updatePermissionById(Permission permission) {
        if (permissionDao.updatePermissionById(permission) != 1) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public void insertOrUpdatePermissionsBatch(List<Permission> permissions) {
        if (permissionDao.insertOrUpdatePermissionsBatch(permissions) != permissions.size()) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public Permission getPermissionById(Integer id) {
        return permissionDao.selectPermissionById(id);
    }

    @Override
    public Page<Permission> getPermissionsByPage(Permission permission, PageRequest pageRequest) {
        long total = permissionDao.selectPermissionsCount(permission);
        return new PageImpl<>(permissionDao.selectPermissionsByPage(permission, pageRequest), pageRequest, total);
    }
}
