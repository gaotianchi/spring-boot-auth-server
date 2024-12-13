package com.gaotianchi.auth.base.service.impl;

import com.gaotianchi.auth.base.dao.RoleBaseDao;
import com.gaotianchi.auth.base.entity.Role;
import com.gaotianchi.auth.base.service.RoleBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author gaotianchi
 * @since 2024-12-07 11:29:50
 */
@Service("roleBaseService")
public class RoleBaseServiceImpl implements RoleBaseService {

    private final RoleBaseDao roleBaseDao;

    public RoleBaseServiceImpl(RoleBaseDao roleBaseDao) {
        Assert.notNull(roleBaseDao, "RoleRepository cannot be null");
        this.roleBaseDao = roleBaseDao;
    }

    @Override
    public void addNewRole(Role role) {
        roleBaseDao.insertRole(role);
    }

    @Override
    public void addNewRolesInBatches(List<Role> roles) {
        roleBaseDao.insertRolesInBatches(roles);
    }

    @Override
    public void removeRoleById(Integer id) {
        roleBaseDao.deleteRoleById(id);
    }

    @Override
    public void removeRolesInBatchesByIds(List<Integer> ids) {
        roleBaseDao.deleteRolesInBatchesByIds(ids);
    }

    @Override
    public void updateRoleById(Role role) {
        roleBaseDao.updateRoleById(role);
    }

    @Override
    public void addNewOrUpdateExistingRolesInBatches(List<Role> roles) {
        roleBaseDao.insertOrUpdateExistingRolesInBatches(roles);
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleBaseDao.selectRoleById(id);
    }

    @Override
    public Page<Role> getRolesByPage(Role role, PageRequest pageRequest) {
        long total = roleBaseDao.countByRole(role);
        return new PageImpl<>(roleBaseDao.selectRolesByPage(role, pageRequest), pageRequest, total);
    }


}
