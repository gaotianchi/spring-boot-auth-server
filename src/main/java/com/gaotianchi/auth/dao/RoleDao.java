package com.gaotianchi.auth.dao;

import com.gaotianchi.auth.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色表(Role)表数据库访问层
 *
 * @author gaotianchi
 * @since 2024-11-27 21:35:13
 */
@Mapper
public interface RoleDao {

    int insert(Role role);

    int deleteById(Integer id);

    int update(Role role);

    Role selectById(Integer id);
}

