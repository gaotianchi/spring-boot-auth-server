package com.gaotianchi.auth.service.impl;

import com.gaotianchi.auth.dao.PermissionDao;
import com.gaotianchi.auth.dao.RoleDao;
import com.gaotianchi.auth.dao.UserDao;
import com.gaotianchi.auth.entity.Permission;
import com.gaotianchi.auth.entity.Role;
import com.gaotianchi.auth.entity.RolePermission;
import com.gaotianchi.auth.entity.UserRole;
import com.gaotianchi.auth.service.RoleAndPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gaotianchi
 * @since 2024/12/18 16:41
 **/
@Service("roleAndPermissionService")
public class RoleAndPermissionServiceImpl implements RoleAndPermissionService {

    private final RoleDao roleDao;
    private final UserDao userDao;
    private final PermissionDao permissionDao;

    public RoleAndPermissionServiceImpl(RoleDao roleDao, UserDao userDao, PermissionDao permissionDao) {
        this.roleDao = roleDao;
        this.userDao = userDao;
        this.permissionDao = permissionDao;
    }

    @Override
    public void updateUserRoles(List<UserRole> userRoles) {
        userDao.insertOrUpdateExistingUserRolesInBatches(userRoles);
    }

    @Override
    public void updateRolePermissions(List<RolePermission> rolePermissions) {
        roleDao.insertOrUpdateExistingRolePermissionsInBatches(rolePermissions);
    }

    @Override
    public List<Role> getAllRolesByUserId(int userId) {
        return roleDao.selectAllRolesByUserId(userId);
    }

    @Override
    public List<Permission> getAllPermissionsByRoleId(int roleId) {
        return permissionDao.selectAllPermissionByRoleId(roleId);
    }
}
