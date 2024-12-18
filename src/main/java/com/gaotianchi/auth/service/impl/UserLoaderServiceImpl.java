package com.gaotianchi.auth.service.impl;

import com.gaotianchi.auth.dao.UserDao;
import com.gaotianchi.auth.entity.User;
import com.gaotianchi.auth.service.UserLoaderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Get user information from SecurityContextHolder.
 * @author gaotianchi
 * @since 2024/12/18 9:11
 **/
@Service("userLoaderService")
public class UserLoaderServiceImpl implements UserLoaderService {

    private final UserDao userDao;

    public UserLoaderServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String loadCurrentLoggedInUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public User loadCurrentLoggedInUser() {
        String username = loadCurrentLoggedInUsername();
        User user = userDao.selectByUsernameOrEmail(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }

    @Override
    public Page<User> getUsersByPage(User user, PageRequest pageRequest) {
        long total = userDao.countByUser(user);
        List<User> users = userDao.selectUsersByPage(user, pageRequest);
        return new PageImpl<>(users, pageRequest, total);
    }
}
