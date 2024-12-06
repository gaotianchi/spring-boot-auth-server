package com.gaotianchi.auth.repository.service;

import com.gaotianchi.auth.repository.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 用户表(User)表服务接口
 *
 * @author gaotianchi
 * @since 2024-12-06 19:28:38
 */
public interface UserRepositoryService {

    void addNewUser(User user);

    void addNewUsersBatch(List<User> users);

    void removeUserById(Integer id);

    void removeUsersBatchByIds(List<Integer> ids);

    void updateUserDetailsById(User user);

    void addNewOrUpdateUsersBatch(List<User> users);

    User getUserById(Integer id);

    Page<User> getUsersByPage(User user, PageRequest pageRequest);
}
