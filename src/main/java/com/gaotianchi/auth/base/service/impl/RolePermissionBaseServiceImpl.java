package com.gaotianchi.auth.base.service.impl;

import com.gaotianchi.auth.base.dao.RolePermissionBaseDao;
import com.gaotianchi.auth.base.entity.RolePermission;
import com.gaotianchi.auth.base.service.RolePermissionBaseService;
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
        rolePermissionBaseDao.insertRolePermission(rolePermission);
    }

    @Override
    public void addNewRolePermissionsInBatches(List<RolePermission> rolePermissions) {
        rolePermissionBaseDao.insertRolePermissionsInBatches(rolePermissions);
    }

    @Override
    public void removeRolePermissionById(Integer id) {
        rolePermissionBaseDao.deleteRolePermissionById(id);
    }

    @Override
    public void removeRolePermissionsInBatchesByIds(List<Integer> ids) {
        rolePermissionBaseDao.deleteRolePermissionsInBatchesByIds(ids);
    }

    @Override
    public void updateRolePermissionById(RolePermission rolePermission) {
        rolePermissionBaseDao.updateRolePermissionById(rolePermission);
    }

    @Override
    public void addNewOrUpdateExistingRolePermissionsInBatches(List<RolePermission> rolePermissions) {
        rolePermissionBaseDao.insertOrUpdateExistingRolePermissionsInBatches(rolePermissions);
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
