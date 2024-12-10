package com.gaotianchi.auth.infrastructure.service;

import com.gaotianchi.auth.infrastructure.entity.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 权限表(Permission)表服务接口
 *
 * @author gaotianchi
 * @since 2024-12-07 11:24:28
 */
public interface PermissionBaseService {

    void addNewPermission(Permission permission);

    void addNewPermissionsInBatches(List<Permission> permissions);

    void removePermissionById(Integer id);

    void removePermissionsInBatchesByIds(List<Integer> ids);

    void updatePermissionById(Permission permission);

    void addNewOrUpdateExistingPermissionsInBatches(List<Permission> permissions);

    Permission getPermissionById(Integer id);

    Page<Permission> getPermissionsByPage(Permission permission, PageRequest pageRequest);
}
