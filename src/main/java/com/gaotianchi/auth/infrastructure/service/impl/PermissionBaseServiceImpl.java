package com.gaotianchi.auth.infrastructure.service.impl;

import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.exception.SQLException;
import com.gaotianchi.auth.infrastructure.dao.PermissionBaseDao;
import com.gaotianchi.auth.infrastructure.entity.Permission;
import com.gaotianchi.auth.infrastructure.service.PermissionBaseService;
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
 * @since 2024-12-07 11:29:50
 */
@Service("permissionBaseService")
public class PermissionBaseServiceImpl implements PermissionBaseService {

    private final PermissionBaseDao permissionBaseDao;

    public PermissionBaseServiceImpl(PermissionBaseDao permissionBaseDao) {
        Assert.notNull(permissionBaseDao, "PermissionRepository cannot be null");
        this.permissionBaseDao = permissionBaseDao;
    }

    @Override
    public void addNewPermission(Permission permission) {
        if (permissionBaseDao.insertPermission(permission) != 1) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void addNewPermissionsBatch(List<Permission> permissions) {
        if (permissionBaseDao.insertPermissionsInBatches(permissions) != permissions.size()) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void removePermissionById(Integer id) {
        if (permissionBaseDao.deletePermissionById(id) != 1) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void removePermissionsBatchByIds(List<Integer> ids) {
        if (permissionBaseDao.deletePermissionsInBatchesByIds(ids) != ids.size()) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void updatePermissionDetailsById(Permission permission) {
        if (permissionBaseDao.updatePermissionById(permission) != 1) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public void addNewOrUpdatePermissionsBatch(List<Permission> permissions) {
        if (permissionBaseDao.insertOrUpdateExistingPermissionsInBatches(permissions) != permissions.size()) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public Permission getPermissionById(Integer id) {
        return permissionBaseDao.selectPermissionById(id);
    }

    @Override
    public Page<Permission> getPermissionsByPage(Permission permission, PageRequest pageRequest) {
        long total = permissionBaseDao.countByPermission(permission);
        return new PageImpl<>(permissionBaseDao.selectPermissionsByPage(permission, pageRequest), pageRequest, total);
    }


}
