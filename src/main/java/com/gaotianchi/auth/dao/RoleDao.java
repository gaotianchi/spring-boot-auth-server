package com.gaotianchi.auth.dao;

import com.gaotianchi.auth.dao.base.RoleBaseDao;
import com.gaotianchi.auth.dao.base.RolePermissionBaseDao;
import com.gaotianchi.auth.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author gaotianchi
 * @since 2024/12/18 15:43
 **/
@Mapper
public interface RoleDao extends RoleBaseDao, RolePermissionBaseDao {
    List<Role> selectAllRolesByUserId(int userId);
}
