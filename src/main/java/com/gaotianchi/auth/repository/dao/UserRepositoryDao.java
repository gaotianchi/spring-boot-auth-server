package com.gaotianchi.auth.repository.dao;

import com.gaotianchi.auth.repository.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 用户表(User)表数据库访问层
 *
 * @author gaotianchi
 * @since 2024-12-06 19:28:38
 */
@Mapper
public interface UserRepositoryDao {

    int insertUser(User user);

    int insertUsersBatch(@Param("entities") List<User> entities);

    int deleteUserById(Integer id);

    int deleteUsersBatchByIds(@Param("ids") List<Integer> ids);

    int updateUserById(User user);

    int insertOrUpdateUsersBatch(@Param("entities") List<User> entities);

    User selectUserById(Integer id);

    long selectUsersCount(User user);

    List<User> selectUsersByPage(User user, @Param("pageable") Pageable pageable);
}

