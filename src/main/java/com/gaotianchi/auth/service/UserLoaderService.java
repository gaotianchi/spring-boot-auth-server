package com.gaotianchi.auth.service;

import com.gaotianchi.auth.entity.User;

/**
 * @author gaotianchi
 * @since 2024/12/18 9:05
 **/
public interface UserLoaderService {
    String loadCurrentLoggedInUsername();

    User loadCurrentLoggedInUser();
}
