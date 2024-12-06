package com.gaotianchi.auth.repository.service;

import com.gaotianchi.auth.repository.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 角色表(Role)表服务接口
 *
 * @author gaotianchi
 * @since 2024-12-06 19:28:39
 */
public interface RoleRepositoryService {

    void addNewRole(Role role);

    void addNewRolesBatch(List<Role> roles);

    void removeRoleById(Integer id);

    void removeRolesBatchByIds(List<Integer> ids);

    void updateRoleDetailsById(Role role);

    void addNewOrUpdateRolesBatch(List<Role> roles);

    Role getRoleById(Integer id);

    Page<Role> getRolesByPage(Role role, PageRequest pageRequest);
}
