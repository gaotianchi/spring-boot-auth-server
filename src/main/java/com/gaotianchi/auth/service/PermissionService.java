package com.gaotianchi.auth.service;

import com.gaotianchi.auth.entity.Permission;

/**
 * 权限表(Permission)表服务接口
 *
 * @author gaotianchi
 * @since 2024-11-28 20:19:44
 */
public interface PermissionService {

    void insert(Permission permission);

    void deleteById(Integer id);

    void update(Permission permission);

    Permission findById(Integer id);
}
