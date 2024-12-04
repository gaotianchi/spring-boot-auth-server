package com.gaotianchi.auth.dao;

import com.gaotianchi.auth.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * 用户表(User)表数据库访问层
 *
 * @author gaotianchi
 * @since 2024-11-27 21:35:13
 */
@Mapper
public interface UserDao {

    int insert(User user);

    int deleteById(Integer id);

    int update(User user);

    User selectById(Integer id);

    User selectByUsernameOrEmail(String usernameOrEmail);

    /**
     * role name with prefix ROLE_, example: {"ROLE_ADMIN", "ALL"}
     *
     * @return java.util.List<java.lang.String>
     * @author gaotianchi
     * @since 2024/11/28 21:00
     **/
    Set<String> selectUserRolesAndPermissionsNamesByUsername(String username);
}

