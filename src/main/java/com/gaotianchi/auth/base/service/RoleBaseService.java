package com.gaotianchi.auth.base.service;

import com.gaotianchi.auth.base.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 角色表(Role)表服务接口
 *
 * @author gaotianchi
 * @since 2024-12-07 11:24:28
 */
public interface RoleBaseService {

    void addNewRole(Role role);

    void addNewRolesInBatches(List<Role> roles);

    void removeRoleById(Integer id);

    void removeRolesInBatchesByIds(List<Integer> ids);

    void updateRoleById(Role role);

    void addNewOrUpdateExistingRolesInBatches(List<Role> roles);

    Role getRoleById(Integer id);

    Page<Role> getRolesByPage(Role role, PageRequest pageRequest);
}
