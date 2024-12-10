package com.gaotianchi.auth.infrastructure.service.impl;

import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.exception.SQLException;
import com.gaotianchi.auth.infrastructure.dao.UserRoleBaseDao;
import com.gaotianchi.auth.infrastructure.entity.UserRole;
import com.gaotianchi.auth.infrastructure.service.UserRoleBaseService;
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
 * @since 2024-12-07 11:29:50
 */
@Service("userRoleBaseService")
public class UserRoleBaseServiceImpl implements UserRoleBaseService {

    private final UserRoleBaseDao userRoleBaseDao;

    public UserRoleBaseServiceImpl(UserRoleBaseDao userRoleBaseDao) {
        Assert.notNull(userRoleBaseDao, "UserRoleRepository cannot be null");
        this.userRoleBaseDao = userRoleBaseDao;
    }

    @Override
    public void addNewUserRole(UserRole userRole) {
        if (userRoleBaseDao.insertUserRole(userRole) != 1) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void addNewUserRolesBatch(List<UserRole> userRoles) {
        if (userRoleBaseDao.insertUserRolesBatch(userRoles) != userRoles.size()) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void removeUserRoleById(Integer id) {
        if (userRoleBaseDao.deleteUserRoleById(id) != 1) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void removeUserRolesBatchByIds(List<Integer> ids) {
        if (userRoleBaseDao.deleteUserRolesBatchByIds(ids) != ids.size()) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void updateUserRoleDetailsById(UserRole userRole) {
        if (userRoleBaseDao.updateUserRoleById(userRole) != 1) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public void addNewOrUpdateUserRolesBatch(List<UserRole> userRoles) {
        if (userRoleBaseDao.insertOrUpdateUserRolesBatch(userRoles) != userRoles.size()) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public UserRole getUserRoleById(Integer id) {
        return userRoleBaseDao.selectUserRoleById(id);
    }

    @Override
    public Page<UserRole> getUserRolesByPage(UserRole userRole, PageRequest pageRequest) {
        long total = userRoleBaseDao.selectUserRolesCount(userRole);
        return new PageImpl<>(userRoleBaseDao.selectUserRolesByPage(userRole, pageRequest), pageRequest, total);
    }


}
