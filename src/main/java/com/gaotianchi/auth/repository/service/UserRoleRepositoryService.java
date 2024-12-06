package com.gaotianchi.auth.repository.service;

import com.gaotianchi.auth.repository.entity.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (UserRole)表服务接口
 *
 * @author gaotianchi
 * @since 2024-12-06 19:28:38
 */
public interface UserRoleRepositoryService {

    void addNewUserRole(UserRole userRole);

    void addNewUserRolesBatch(List<UserRole> userRoles);

    void removeUserRoleById(Integer id);

    void removeUserRolesBatchByIds(List<Integer> ids);

    void updateUserRoleDetailsById(UserRole userRole);

    void addNewOrUpdateUserRolesBatch(List<UserRole> userRoles);

    UserRole getUserRoleById(Integer id);

    Page<UserRole> getUserRolesByPage(UserRole userRole, PageRequest pageRequest);
}
