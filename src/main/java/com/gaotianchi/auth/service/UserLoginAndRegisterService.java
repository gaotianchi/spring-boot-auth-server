package com.gaotianchi.auth.service;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * User login and registration related business logic
 *
 * @author gaotianchi
 * @since 2024-11-28 20:19:44
 */
public interface UserLoginAndRegisterService extends UserDetailsService {

    // =============================== User registration =================================== //

    void registerUserViaEmailAndSendVerificationCode(String email, String password);

    void verifyEmail(String email, int verificationCode);
}
