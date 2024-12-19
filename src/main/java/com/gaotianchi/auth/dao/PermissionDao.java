package com.gaotianchi.auth.dao;

import com.gaotianchi.auth.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author gaotianchi
 * @since 2024/12/19 9:27
 **/
@Mapper
public interface PermissionDao {
    List<Permission> selectAllPermissionByRoleId(int roleId);
}
