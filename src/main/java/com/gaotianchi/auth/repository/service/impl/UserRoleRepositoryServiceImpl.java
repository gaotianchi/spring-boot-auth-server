package com.gaotianchi.auth.repository.service.impl;

import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.exception.SQLException;
import com.gaotianchi.auth.repository.dao.UserRoleRepositoryDao;
import com.gaotianchi.auth.repository.entity.UserRole;
import com.gaotianchi.auth.repository.service.UserRoleRepositoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * (UserRole)表服务实现类
 *
 * @author gaotianchi
 * @since 2024-12-06 19:30:49
 */
@Service("userRoleService")
public class UserRoleRepositoryServiceImpl implements UserRoleRepositoryService {

    private final UserRoleRepositoryDao userRoleRepositoryDao;

    public UserRoleRepositoryServiceImpl(UserRoleRepositoryDao userRoleRepositoryDao) {
        Assert.notNull(userRoleRepositoryDao, "UserRoleRepository cannot be null");
        this.userRoleRepositoryDao = userRoleRepositoryDao;
    }

    @Override
    public void addNewUserRole(UserRole userRole) {
        if (userRoleRepositoryDao.insertUserRole(userRole) != 1) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void addNewUserRolesBatch(List<UserRole> userRoles) {
        if (userRoleRepositoryDao.insertUserRolesBatch(userRoles) != userRoles.size()) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void removeUserRoleById(Integer id) {
        if (userRoleRepositoryDao.deleteUserRoleById(id) != 1) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void removeUserRolesBatchByIds(List<Integer> ids) {
        if (userRoleRepositoryDao.deleteUserRolesBatchByIds(ids) != ids.size()) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void updateUserRoleDetailsById(UserRole userRole) {
        if (userRoleRepositoryDao.updateUserRoleById(userRole) != 1) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public void addNewOrUpdateUserRolesBatch(List<UserRole> userRoles) {
        if (userRoleRepositoryDao.insertOrUpdateUserRolesBatch(userRoles) != userRoles.size()) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public UserRole getUserRoleById(Integer id) {
        return userRoleRepositoryDao.selectUserRoleById(id);
    }

    @Override
    public Page<UserRole> getUserRolesByPage(UserRole userRole, PageRequest pageRequest) {
        long total = userRoleRepositoryDao.selectUserRolesCount(userRole);
        return new PageImpl<>(userRoleRepositoryDao.selectUserRolesByPage(userRole, pageRequest), pageRequest, total);
    }


}
