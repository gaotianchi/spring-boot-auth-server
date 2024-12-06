package com.gaotianchi.auth.repository.service;

import com.gaotianchi.auth.repository.entity.RolePermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 角色权限表(RolePermission)表服务接口
 *
 * @author gaotianchi
 * @since 2024-12-06 19:28:39
 */
public interface RolePermissionRepositoryService {

    void addNewRolePermission(RolePermission rolePermission);

    void addNewRolePermissionsBatch(List<RolePermission> rolePermissions);

    void removeRolePermissionById(Integer id);

    void removeRolePermissionsBatchByIds(List<Integer> ids);

    void updateRolePermissionDetailsById(RolePermission rolePermission);

    void addNewOrUpdateRolePermissionsBatch(List<RolePermission> rolePermissions);

    RolePermission getRolePermissionById(Integer id);

    Page<RolePermission> getRolePermissionsByPage(RolePermission rolePermission, PageRequest pageRequest);
}
