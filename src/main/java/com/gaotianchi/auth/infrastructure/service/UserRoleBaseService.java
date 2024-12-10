package com.gaotianchi.auth.infrastructure.service;

import com.gaotianchi.auth.infrastructure.entity.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (UserRole)表服务接口
 *
 * @author gaotianchi
 * @since 2024-12-07 11:24:29
 */
public interface UserRoleBaseService {

    void addNewUserRole(UserRole userRole);

    void addNewUserRolesInBatches(List<UserRole> userRoles);

    void removeUserRoleById(Integer id);

    void removeUserRolesInBatchesByIds(List<Integer> ids);

    void updateUserRoleById(UserRole userRole);

    void addNewOrUpdateExistingUserRolesInBatches(List<UserRole> userRoles);

    UserRole getUserRoleById(Integer id);

    Page<UserRole> getUserRolesByPage(UserRole userRole, PageRequest pageRequest);
}
