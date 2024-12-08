package com.gaotianchi.auth.repository.dao;

import com.gaotianchi.auth.repository.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 角色表(Role)表数据库访问层
 *
 * @author gaotianchi
 * @since 2024-12-06 19:28:39
 */
@Mapper
public interface RoleRepositoryDao {

    int insertRole(Role role);

    int insertRolesBatch(@Param("entities") List<Role> entities);

    int deleteRoleById(Integer id);

    int deleteRolesBatchByIds(@Param("ids") List<Integer> ids);

    int updateRoleById(Role role);

    int insertOrUpdateRolesBatch(@Param("entities") List<Role> entities);

    Role selectRoleById(Integer id);

    long selectRolesCount(Role role);

    List<Role> selectRolesByPage(Role role, @Param("pageable") Pageable pageable);
}

