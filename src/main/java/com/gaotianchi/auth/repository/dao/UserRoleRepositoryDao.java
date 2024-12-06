package com.gaotianchi.auth.repository.dao;

import com.gaotianchi.auth.repository.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (UserRole)表数据库访问层
 *
 * @author gaotianchi
 * @since 2024-12-06 19:28:38
 */
@Mapper
public interface UserRoleRepositoryDao {

    int insertUserRole(UserRole userRole);

    int insertUserRolesBatch(@Param("entities") List<UserRole> entities);

    int deleteUserRoleById(Integer id);

    int deleteUserRolesBatchByIds(@Param("ids") List<Integer> ids);

    int updateUserRoleById(UserRole userRole);

    int insertOrUpdateUserRolesBatch(@Param("entities") List<UserRole> entities);

    UserRole selectUserRoleById(Integer id);

    long selectUserRolesCount(UserRole userRole);

    List<UserRole> selectUserRolesByPage(UserRole userRole, @Param("pageable") Pageable pageable);
}

