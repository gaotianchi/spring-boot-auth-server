package com.gaotianchi.auth.repository.service.impl;

import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.exception.SQLException;
import com.gaotianchi.auth.repository.dao.UserRepositoryDao;
import com.gaotianchi.auth.repository.entity.User;
import com.gaotianchi.auth.repository.service.UserRepositoryService;
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
 * @since 2024-12-06 19:30:49
 */
@Service("userService")
public class UserRepositoryServiceImpl implements UserRepositoryService {

    private final UserRepositoryDao userRepositoryDao;

    public UserRepositoryServiceImpl(UserRepositoryDao userRepositoryDao) {
        Assert.notNull(userRepositoryDao, "UserRepository cannot be null");
        this.userRepositoryDao = userRepositoryDao;
    }

    @Override
    public void addNewUser(User user) {
        if (userRepositoryDao.insertUser(user) != 1) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void addNewUsersBatch(List<User> users) {
        if (userRepositoryDao.insertUsersBatch(users) != users.size()) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void removeUserById(Integer id) {
        if (userRepositoryDao.deleteUserById(id) != 1) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void removeUsersBatchByIds(List<Integer> ids) {
        if (userRepositoryDao.deleteUsersBatchByIds(ids) != ids.size()) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void updateUserDetailsById(User user) {
        if (userRepositoryDao.updateUserById(user) != 1) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public void addNewOrUpdateUsersBatch(List<User> users) {
        if (userRepositoryDao.insertOrUpdateUsersBatch(users) != users.size()) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public User getUserById(Integer id) {
        return userRepositoryDao.selectUserById(id);
    }

    @Override
    public Page<User> getUsersByPage(User user, PageRequest pageRequest) {
        long total = userRepositoryDao.selectUsersCount(user);
        return new PageImpl<>(userRepositoryDao.selectUsersByPage(user, pageRequest), pageRequest, total);
    }


}
