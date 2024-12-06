package com.gaotianchi.auth.repository.service.impl;

import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.exception.SQLException;
import com.gaotianchi.auth.repository.dao.RoleRepositoryDao;
import com.gaotianchi.auth.repository.entity.Role;
import com.gaotianchi.auth.repository.service.RoleRepositoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 角色表(Role)表服务实现类
 *
 * @author gaotianchi
 * @since 2024-12-06 19:30:49
 */
@Service("roleService")
public class RoleRepositoryServiceImpl implements RoleRepositoryService {

    private final RoleRepositoryDao roleRepositoryDao;

    public RoleRepositoryServiceImpl(RoleRepositoryDao roleRepositoryDao) {
        Assert.notNull(roleRepositoryDao, "RoleRepository cannot be null");
        this.roleRepositoryDao = roleRepositoryDao;
    }

    @Override
    public void addNewRole(Role role) {
        if (roleRepositoryDao.insertRole(role) != 1) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void addNewRolesBatch(List<Role> roles) {
        if (roleRepositoryDao.insertRolesBatch(roles) != roles.size()) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void removeRoleById(Integer id) {
        if (roleRepositoryDao.deleteRoleById(id) != 1) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void removeRolesBatchByIds(List<Integer> ids) {
        if (roleRepositoryDao.deleteRolesBatchByIds(ids) != ids.size()) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void updateRoleDetailsById(Role role) {
        if (roleRepositoryDao.updateRoleById(role) != 1) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public void addNewOrUpdateRolesBatch(List<Role> roles) {
        if (roleRepositoryDao.insertOrUpdateRolesBatch(roles) != roles.size()) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleRepositoryDao.selectRoleById(id);
    }

    @Override
    public Page<Role> getRolesByPage(Role role, PageRequest pageRequest) {
        long total = roleRepositoryDao.selectRolesCount(role);
        return new PageImpl<>(roleRepositoryDao.selectRolesByPage(role, pageRequest), pageRequest, total);
    }


}
