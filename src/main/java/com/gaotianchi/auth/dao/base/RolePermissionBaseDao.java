package com.gaotianchi.auth.dao.base;

import com.gaotianchi.auth.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 角色权限表(RolePermission)表数据库访问层
 *
 * @author gaotianchi
 * @since 2024-12-07 11:24:28
 */
@Mapper
public interface RolePermissionBaseDao {

    void insertRolePermission(RolePermission rolePermission);

    void insertRolePermissionsInBatches(@Param("entities") List<RolePermission> entities);

    void deleteRolePermissionById(Integer id);

    void deleteRolePermissionsInBatchesByIds(@Param("ids") List<Integer> ids);

    void updateRolePermissionById(RolePermission rolePermission);

    void insertOrUpdateExistingRolePermissionsInBatches(@Param("entities") List<RolePermission> entities);

    RolePermission selectRolePermissionById(Integer id);

    long countByRolePermission(RolePermission rolePermission);

    List<RolePermission> selectRolePermissionsByPage(RolePermission rolePermission, @Param("pageable") Pageable pageable);
}

