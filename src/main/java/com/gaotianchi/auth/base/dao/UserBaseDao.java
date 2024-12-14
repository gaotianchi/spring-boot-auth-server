package com.gaotianchi.auth.base.dao;

import com.gaotianchi.auth.base.entity.User;
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

    void insertUser(User user);

    void insertUsersInBatches(@Param("entities") List<User> entities);

    void deleteUserById(Integer id);

    void deleteUsersInBatchesByIds(@Param("ids") List<Integer> ids);

    void updateUserById(User user);

    void insertOrUpdateExistingUsersInBatches(@Param("entities") List<User> entities);

    User selectUserById(Integer id);

    long countByUser(User user);

    List<User> selectUsersByPage(User user, @Param("pageable") Pageable pageable);
}

