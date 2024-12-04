package com.gaotianchi.auth.dao;

import com.gaotianchi.auth.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

/**
 * 权限表(Permission)表数据库访问层
 *
 * @author gaotianchi
 * @since 2024-11-27 21:35:13
 */
@Mapper
public interface PermissionDao {

    int insert(Permission permission);

    int deleteById(Integer id);

    int update(Permission permission);

    Permission selectById(Integer id);
}

