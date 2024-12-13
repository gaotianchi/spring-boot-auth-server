package com.gaotianchi.auth.base.dao;

import com.gaotianchi.auth.base.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 角色表(Role)表数据库访问层
 *
 * @author gaotianchi
 * @since 2024-12-07 11:24:28
 */
@Mapper
public interface RoleBaseDao {

    void insertRole(Role role);

    void insertRolesInBatches(@Param("entities") List<Role> entities);

    void deleteRoleById(Integer id);

    void deleteRolesInBatchesByIds(@Param("ids") List<Integer> ids);

    void updateRoleById(Role role);

    void insertOrUpdateExistingRolesInBatches(@Param("entities") List<Role> entities);

    Role selectRoleById(Integer id);

    long countByRole(Role role);

    List<Role> selectRolesByPage(Role role, @Param("pageable") Pageable pageable);
}

