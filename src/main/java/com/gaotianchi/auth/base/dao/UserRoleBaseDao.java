package com.gaotianchi.auth.base.dao;

import com.gaotianchi.auth.base.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (UserRole)表数据库访问层
 *
 * @author gaotianchi
 * @since 2024-12-07 11:24:29
 */
@Mapper
public interface UserRoleBaseDao {

    int insertUserRole(UserRole userRole);

    int insertUserRolesInBatches(@Param("entities") List<UserRole> entities);

    int deleteUserRoleById(Integer id);

    int deleteUserRolesInBatchesByIds(@Param("ids") List<Integer> ids);

    int updateUserRoleById(UserRole userRole);

    int insertOrUpdateExistingUserRolesInBatches(@Param("entities") List<UserRole> entities);

    UserRole selectUserRoleById(Integer id);

    long countByUserRole(UserRole userRole);

    List<UserRole> selectUserRolesByPage(UserRole userRole, @Param("pageable") Pageable pageable);
}

