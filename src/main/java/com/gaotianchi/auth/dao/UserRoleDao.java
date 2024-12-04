package com.gaotianchi.auth.dao;

import com.gaotianchi.auth.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * (UserRole)表数据库访问层
 *
 * @author gaotianchi
 * @since 2024-11-27 21:35:13
 */
@Mapper
public interface UserRoleDao {

    int insert(UserRole userRole);

    int deleteById(Integer id);

    int update(UserRole userRole);

    UserRole selectById(Integer id);
}

