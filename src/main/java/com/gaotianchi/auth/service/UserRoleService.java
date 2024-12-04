package com.gaotianchi.auth.service;

import com.gaotianchi.auth.entity.UserRole;

/**
 * (UserRole)表服务接口
 *
 * @author gaotianchi
 * @since 2024-11-28 20:19:44
 */
public interface UserRoleService {

    void insert(UserRole userRole);

    void deleteById(Integer id);

    void update(UserRole userRole);

    UserRole findById(Integer id);
}
