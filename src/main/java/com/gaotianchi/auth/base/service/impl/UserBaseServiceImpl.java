package com.gaotianchi.auth.base.service.impl;

import com.gaotianchi.auth.base.dao.UserBaseDao;
import com.gaotianchi.auth.base.entity.User;
import com.gaotianchi.auth.base.service.UserBaseService;
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
        userBaseDao.insertUser(user);
    }

    @Override
    public void addNewUsersInBatches(List<User> users) {
        userBaseDao.insertUsersInBatches(users);
    }

    @Override
    public void removeUserById(Integer id) {
        userBaseDao.deleteUserById(id);
    }

    @Override
    public void removeUsersInBatchesByIds(List<Integer> ids) {
        userBaseDao.deleteUsersInBatchesByIds(ids);
    }

    @Override
    public void updateUserById(User user) {
        userBaseDao.updateUserById(user);
    }

    @Override
    public void addNewOrUpdateExistingUsersInBatches(List<User> users) {
        userBaseDao.insertOrUpdateExistingUsersInBatches(users);
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
