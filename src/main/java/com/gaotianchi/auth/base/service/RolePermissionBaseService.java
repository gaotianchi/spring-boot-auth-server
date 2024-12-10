package com.gaotianchi.auth.base.service;

import com.gaotianchi.auth.base.entity.RolePermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 角色权限表(RolePermission)表服务接口
 *
 * @author gaotianchi
 * @since 2024-12-07 11:24:28
 */
public interface RolePermissionBaseService {

    void addNewRolePermission(RolePermission rolePermission);

    void addNewRolePermissionsInBatches(List<RolePermission> rolePermissions);

    void removeRolePermissionById(Integer id);

    void removeRolePermissionsInBatchesByIds(List<Integer> ids);

    void updateRolePermissionById(RolePermission rolePermission);

    void addNewOrUpdateExistingRolePermissionsInBatches(List<RolePermission> rolePermissions);

    RolePermission getRolePermissionById(Integer id);

    Page<RolePermission> getRolePermissionsByPage(RolePermission rolePermission, PageRequest pageRequest);
}
