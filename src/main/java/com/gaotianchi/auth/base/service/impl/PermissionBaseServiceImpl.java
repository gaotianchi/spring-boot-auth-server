package com.gaotianchi.auth.base.service.impl;

import com.gaotianchi.auth.base.dao.PermissionBaseDao;
import com.gaotianchi.auth.base.entity.Permission;
import com.gaotianchi.auth.base.service.PermissionBaseService;
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
        permissionBaseDao.insertPermission(permission);
    }

    @Override
    public void addNewPermissionsInBatches(List<Permission> permissions) {
        permissionBaseDao.insertPermissionsInBatches(permissions);
    }

    @Override
    public void removePermissionById(Integer id) {
        permissionBaseDao.deletePermissionById(id);
    }

    @Override
    public void removePermissionsInBatchesByIds(List<Integer> ids) {
        permissionBaseDao.deletePermissionsInBatchesByIds(ids);
    }

    @Override
    public void updatePermissionById(Permission permission) {
        permissionBaseDao.updatePermissionById(permission);
    }

    @Override
    public void addNewOrUpdateExistingPermissionsInBatches(List<Permission> permissions) {
        permissionBaseDao.insertOrUpdateExistingPermissionsInBatches(permissions);
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
