package com.gaotianchi.auth.repository.dao;

import com.gaotianchi.auth.repository.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 权限表(Permission)表数据库访问层
 *
 * @author gaotianchi
 * @since 2024-12-06 19:28:39
 */
@Mapper
public interface PermissionRepositoryDao {

    int insertPermission(Permission permission);

    int insertPermissionsBatch(@Param("entities") List<Permission> entities);

    int deletePermissionById(Integer id);

    int deletePermissionsBatchByIds(@Param("ids") List<Integer> ids);

    int updatePermissionById(Permission permission);

    int insertOrUpdatePermissionsBatch(@Param("entities") List<Permission> entities);

    Permission selectPermissionById(Integer id);

    long selectPermissionsCount(Permission permission);

    List<Permission> selectPermissionsByPage(Permission permission, @Param("pageable") Pageable pageable);
}

