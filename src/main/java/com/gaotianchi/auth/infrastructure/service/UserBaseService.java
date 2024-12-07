package com.gaotianchi.auth.infrastructure.service;

import com.gaotianchi.auth.infrastructure.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 用户表(User)表服务接口
 *
 * @author gaotianchi
 * @since 2024-12-07 11:24:29
 */
public interface UserBaseService {

    void addNewUser(User user);

    void addNewUsersBatch(List<User> users);

    void removeUserById(Integer id);

    void removeUsersBatchByIds(List<Integer> ids);

    void updateUserDetailsById(User user);

    void addNewOrUpdateUsersBatch(List<User> users);

    User getUserById(Integer id);

    Page<User> getUsersByPage(User user, PageRequest pageRequest);
}
