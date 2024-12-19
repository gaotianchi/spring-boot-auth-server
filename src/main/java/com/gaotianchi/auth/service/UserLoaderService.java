package com.gaotianchi.auth.service;

import com.gaotianchi.auth.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @author gaotianchi
 * @since 2024/12/18 9:05
 **/
public interface UserLoaderService {
    String loadCurrentLoggedInUsername();

    User loadCurrentLoggedInUser();

    Page<User> getUsersByPage(User user, PageRequest pageRequest);

    Page<User> getUsersWithCertainRoles(List<Integer> roleCodes, PageRequest pageRequest);

    Page<User> getUsersWithCertainPermissions(List<Integer> permissionCodes, PageRequest pageRequest);
}
