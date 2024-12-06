package com.gaotianchi.auth.repository.dao;

import com.gaotianchi.auth.repository.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 角色权限表(RolePermission)表数据库访问层
 *
 * @author gaotianchi
 * @since 2024-12-06 19:28:39
 */
@Mapper
public interface RolePermissionRepositoryDao {

    int insertRolePermission(RolePermission rolePermission);

    int insertRolePermissionsBatch(@Param("entities") List<RolePermission> entities);

    int deleteRolePermissionById(Integer id);

    int deleteRolePermissionsBatchByIds(@Param("ids") List<Integer> ids);

    int updateRolePermissionById(RolePermission rolePermission);

    int insertOrUpdateRolePermissionsBatch(@Param("entities") List<RolePermission> entities);

    RolePermission selectRolePermissionById(Integer id);

    long selectRolePermissionsCount(RolePermission rolePermission);

    List<RolePermission> selectRolePermissionsByPage(RolePermission rolePermission, @Param("pageable") Pageable pageable);
}

