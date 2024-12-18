package com.gaotianchi.auth.dao;

import com.gaotianchi.auth.dao.base.UserBaseDao;
import com.gaotianchi.auth.dao.base.UserRoleBaseDao;
import com.gaotianchi.auth.entity.Role;
import com.gaotianchi.auth.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * 用户表(User)表数据库访问层
 *
 * @author gaotianchi
 * @since 2024-11-27 21:35:13
 */
@Mapper
public interface UserDao extends UserBaseDao, UserRoleBaseDao {

    User selectByUsernameOrEmail(String usernameOrEmail);

    /**
     * role name with prefix ROLE_, example: {"ROLE_ADMIN", "ALL"}
     * @return set of user permission or role name
     * @author gaotianchi
     * @since 2024/11/28 21:00
     **/
    Set<String> selectUserRolesAndPermissionsNamesByUsernameOrEmail(String usernameOrEmail);

    List<Role> selectAllRolesByUserId(int userId);
}

