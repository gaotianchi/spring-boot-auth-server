package com.gaotianchi.auth.service;

import com.gaotianchi.auth.entity.RolePermission;

/**
 * 角色权限表(RolePermission)表服务接口
 *
 * @author gaotianchi
 * @since 2024-11-28 20:19:44
 */
public interface RolePermissionService {

    void insert(RolePermission rolePermission);

    void deleteById(Integer id);

    void update(RolePermission rolePermission);

    RolePermission findById(Integer id);
}
