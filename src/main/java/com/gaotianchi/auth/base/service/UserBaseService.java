package com.gaotianchi.auth.base.service;

import com.gaotianchi.auth.base.entity.User;
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

    void addNewUsersInBatches(List<User> users);

    void removeUserById(Integer id);

    void removeUsersInBatchesByIds(List<Integer> ids);

    void updateUserById(User user);

    void addNewOrUpdateExistingUsersInBatches(List<User> users);

    User getUserById(Integer id);

    Page<User> getUsersByPage(User user, PageRequest pageRequest);
}
