package com.gaotianchi.auth.dao;

import com.gaotianchi.auth.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色权限表(RolePermission)表数据库访问层
 *
 * @author gaotianchi
 * @since 2024-11-27 21:35:13
 */
@Mapper
public interface RolePermissionDao {

    int insert(RolePermission rolePermission);

    int deleteById(Integer id);

    int update(RolePermission rolePermission);

    RolePermission selectById(Integer id);
}

