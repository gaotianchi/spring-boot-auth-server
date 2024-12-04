package com.gaotianchi.auth.service;

import com.gaotianchi.auth.entity.Role;

/**
 * 角色表(Role)表服务接口
 *
 * @author gaotianchi
 * @since 2024-11-28 20:19:44
 */
public interface RoleService {

    void insert(Role role);

    void deleteById(Integer id);

    void update(Role role);

    Role findById(Integer id);
}
