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

    /**
     * Register a new user via email and send verification code
     *
     * @param email    email
     * @param password password
     * @author gaotianchi
     * @since 2024/12/16 16:40
     **/
    void registerUserViaEmailAndSendVerificationCode(String email, String password);

    /**
     * Verify the email address and verification code
     * @author gaotianchi
     * @since 2024/12/16 16:41
     * @param email email
     * @param verificationCode verification code
     **/
    void verifyEmail(String email, int verificationCode);


    // ========================================= deregister ============================== //

    /**
     * Deregister a user via email and send verification code
     *
     * @param email email
     * @author gaotianchi
     * @since 2024/12/16 17:09
     **/
    void deregisterUserViaEmailAndSendVerificationCode(String email);


    /**
     * Confirm deregister a user via email and verification code
     *
     * @param email            email
     * @param verificationCode verification code
     * @author gaotianchi
     * @since 2024/12/16 17:17
     **/
    void confirmDeregisterUserViaEmailAndVerificationCode(String email, int verificationCode);

}
