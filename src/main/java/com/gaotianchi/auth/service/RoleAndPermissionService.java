package com.gaotianchi.auth.service;

import com.gaotianchi.auth.entity.Permission;
import com.gaotianchi.auth.entity.Role;
import com.gaotianchi.auth.entity.RolePermission;
import com.gaotianchi.auth.entity.UserRole;

import java.util.List;

/**
 * Manage user role and permission.
 *
 * @author gaotianchi
 * @since 2024/12/18 14:39
 **/
public interface RoleAndPermissionService {

    /**
     * Update user roles in batches.
     *
     * @param userRoles User role list
     * @author gaotianchi
     * @since 2024/12/18 15:02
     **/
    void updateUserRoles(List<UserRole> userRoles);

    /**
     * Update role permissions in batches.
     *
     * @param rolePermissions Role permission list
     * @author gaotianchi
     * @since 2024/12/18 15:04
     **/
    void updateRolePermissions(List<RolePermission> rolePermissions);

    List<Role> getAllRolesByUserId(int userId);

    List<Permission> getAllPermissionsByRoleId(int roleId);
}
