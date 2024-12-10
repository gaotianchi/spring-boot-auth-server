package com.gaotianchi.auth.infrastructure.dao;

import com.gaotianchi.auth.infrastructure.entity.User;
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

