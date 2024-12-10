package com.gaotianchi.auth.infrastructure.service.impl;

import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.exception.SQLException;
import com.gaotianchi.auth.infrastructure.dao.UserBaseDao;
import com.gaotianchi.auth.infrastructure.entity.User;
import com.gaotianchi.auth.infrastructure.service.UserBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 用户表(User)表服务实现类
 *
 * @author gaotianchi
 * @since 2024-12-07 11:29:50
 */
@Service("userBaseService")
public class UserBaseServiceImpl implements UserBaseService {

    private final UserBaseDao userBaseDao;

    public UserBaseServiceImpl(UserBaseDao userBaseDao) {
        Assert.notNull(userBaseDao, "UserRepository cannot be null");
        this.userBaseDao = userBaseDao;
    }

    @Override
    public void addNewUser(User user) {
        if (userBaseDao.insertUser(user) != 1) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void addNewUsersInBatches(List<User> users) {
        if (userBaseDao.insertUsersInBatches(users) != users.size()) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void removeUserById(Integer id) {
        if (userBaseDao.deleteUserById(id) != 1) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void removeUsersInBatchesByIds(List<Integer> ids) {
        if (userBaseDao.deleteUsersInBatchesByIds(ids) != ids.size()) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void updateUserById(User user) {
        if (userBaseDao.updateUserById(user) != 1) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public void addNewOrUpdateExistingUsersInBatches(List<User> users) {
        if (userBaseDao.insertOrUpdateExistingUsersInBatches(users) != users.size()) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public User getUserById(Integer id) {
        return userBaseDao.selectUserById(id);
    }

    @Override
    public Page<User> getUsersByPage(User user, PageRequest pageRequest) {
        long total = userBaseDao.countByUser(user);
        return new PageImpl<>(userBaseDao.selectUsersByPage(user, pageRequest), pageRequest, total);
    }


}
