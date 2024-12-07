package com.gaotianchi.auth.infrastructure.service;

import com.gaotianchi.auth.infrastructure.entity.Role;
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

    void addNewRolesBatch(List<Role> roles);

    void removeRoleById(Integer id);

    void removeRolesBatchByIds(List<Integer> ids);

    void updateRoleDetailsById(Role role);

    void addNewOrUpdateRolesBatch(List<Role> roles);

    Role getRoleById(Integer id);

    Page<Role> getRolesByPage(Role role, PageRequest pageRequest);
}
