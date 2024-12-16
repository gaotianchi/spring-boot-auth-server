package com.gaotianchi.auth.dao.base;

import com.gaotianchi.auth.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 用户表(User)表数据库访问层
 *
 * @author gaotianchi
 * @since 2024-12-07 11:24:29
 */
@Mapper
public interface UserBaseDao {

    /**
     * username, password and email are required, others are optional
     *
     * @param user User
     * @author gaotianchi
     * @since 2024/12/16 10:48
     **/
    int insertUser(User user);

    int insertUsersInBatches(@Param("entities") List<User> entities);

    int deleteUserById(Integer id);

    int deleteUsersInBatchesByIds(@Param("ids") List<Integer> ids);

    int updateUserById(User user);

    int insertOrUpdateExistingUsersInBatches(@Param("entities") List<User> entities);

    User selectUserById(Integer id);

    long countByUser(User user);

    List<User> selectUsersByPage(User user, @Param("pageable") Pageable pageable);
}

