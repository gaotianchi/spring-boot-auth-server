package com.gaotianchi.auth.dao.base;

import com.gaotianchi.auth.entity.UserRole;
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

    void insertUserRole(UserRole userRole);

    void insertUserRolesInBatches(@Param("entities") List<UserRole> entities);

    void deleteUserRoleById(Integer id);

    void deleteUserRolesInBatchesByIds(@Param("ids") List<Integer> ids);

    void updateUserRoleById(UserRole userRole);

    void insertOrUpdateExistingUserRolesInBatches(@Param("entities") List<UserRole> entities);

    UserRole selectUserRoleById(Integer id);

    long countByUserRole(UserRole userRole);

    List<UserRole> selectUserRolesByPage(UserRole userRole, @Param("pageable") Pageable pageable);
}

