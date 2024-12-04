package com.gaotianchi.auth.service.impl;

import com.gaotianchi.auth.dao.RoleDao;
import com.gaotianchi.auth.entity.Role;
import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.exception.SQLException;
import com.gaotianchi.auth.service.RoleService;
import org.springframework.stereotype.Service;


/**
 * 角色表(Role)表服务实现类
 *
 * @author gaotianchi
 * @since 2024-11-28 20:45:39
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public void insert(Role role) {
        if (roleDao.insert(role) != 1) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void deleteById(Integer id) {
        if (roleDao.deleteById(id) != 1) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void update(Role role) {
        if (roleDao.update(role) != 1) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public Role findById(Integer id) {
        return roleDao.selectById(id);
    }
}
