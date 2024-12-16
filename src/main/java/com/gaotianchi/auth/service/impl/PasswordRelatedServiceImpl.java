package com.gaotianchi.auth.service.impl;

import com.gaotianchi.auth.base.entity.User;
import com.gaotianchi.auth.dao.UserDao;
import com.gaotianchi.auth.service.PasswordRelatedService;
import com.gaotianchi.auth.utils.ServiceTool;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author gaotianchi
 * @since 2024/12/16 16:13
 **/
@Service("passwordRelatedService")
public class PasswordRelatedServiceImpl implements PasswordRelatedService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public PasswordRelatedServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void updatePassword(String usernameOrEmail, String oldPassword, String newPassword) {
        // 1. Load user by username or email.
        User user = userDao.selectByUsernameOrEmail(usernameOrEmail);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // 2. Check old password.
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }

        // 3. Update password.
        user.setPassword(passwordEncoder.encode(newPassword));
        int rows = userDao.updateUserById(user);
        if (rows != 1) {
            throw new RuntimeException("Update password failed");
        }

    }

    @Override
    public void sendResetPasswordEmail(String email) {
        // 1. Load user by email.
        User user = userDao.selectByUsernameOrEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // 2. Generate verification code and update to user entity.
        int verificationCode = ServiceTool.generateVerificationCode();
        user.setVerificationCode(verificationCode);
        int rows = userDao.updateUserById(user);
        if (rows != 1) {
            throw new RuntimeException("Update verification code failed");
        }

        // 3. TODO: Send an email with verification code.

    }

    @Override
    public void resetPassword(String email, String verificationCode, String newPassword) {
        // 1. Load user by email.
        User user = userDao.selectByUsernameOrEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // 2. Check verification code.
        if (!user.getVerificationCode().toString().equals(verificationCode)) {
            throw new RuntimeException("Verification code is incorrect");
        }

        // 3. Update password.
        user.setPassword(passwordEncoder.encode(newPassword));
        int rows = userDao.updateUserById(user);
        if (rows != 1) {
            throw new RuntimeException("Update password failed");
        }

    }
}
