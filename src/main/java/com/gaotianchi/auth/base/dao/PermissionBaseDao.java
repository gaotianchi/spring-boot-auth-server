package com.gaotianchi.auth.base.dao;

import com.gaotianchi.auth.base.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author gaotianchi
 * @since 2024-12-07 11:24:28
 */
@Mapper
public interface PermissionBaseDao {

    void insertPermission(Permission permission);

    void insertPermissionsInBatches(@Param("entities") List<Permission> entities);

    void deletePermissionById(Integer id);

    void deletePermissionsInBatchesByIds(@Param("ids") List<Integer> ids);

    void updatePermissionById(Permission permission);

    void insertOrUpdateExistingPermissionsInBatches(@Param("entities") List<Permission> entities);

    Permission selectPermissionById(Integer id);

    long countByPermission(Permission permission);

    List<Permission> selectPermissionsByPage(Permission permission, @Param("pageable") Pageable pageable);
}

