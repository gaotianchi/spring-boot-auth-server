package com.gaotianchi.auth.service;

/**
 * @author gaotianchi
 * @since 2024/12/16 15:14
 **/
public interface PasswordRelatedService {
    /**
     * User update password, check old password and update password.
     * Prerequisite: User must be logged in.
     * @param oldPassword     old password
     * @param newPassword     new password
     * @author gaotianchi
     * @since 2024/12/16 16:12
     **/
    void updatePassword(String oldPassword, String newPassword);


    /**
     * User forget password, send verification code to user's email.
     *
     * @param email user email
     * @author gaotianchi
     * @since 2024/12/16 16:09
     **/
    void sendResetPasswordEmail(String email);


    /**
     * User reset password, check verification code and reset password.
     *
     * @param email            email
     * @param verificationCode email verification code
     * @param newPassword      new password
     * @author gaotianchi
     * @since 2024/12/16 16:12
     **/
    void resetPassword(String email, String verificationCode, String newPassword);
}
