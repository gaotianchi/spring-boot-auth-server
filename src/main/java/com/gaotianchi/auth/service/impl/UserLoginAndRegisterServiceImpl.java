package com.gaotianchi.auth.service.impl;

import com.gaotianchi.auth.dao.UserDao;
import com.gaotianchi.auth.entity.User;
import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.exception.SQLException;
import com.gaotianchi.auth.service.UserLoginAndRegisterService;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import static com.gaotianchi.auth.utils.ServiceTool.generateVerificationCode;


/**
 * User login and registration related business logic
 * @author gaotianchi
 * @since 2024-11-28 20:45:39
 */
@Service("userLoginAndRegisterService")
public class UserLoginAndRegisterServiceImpl implements UserLoginAndRegisterService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserLoginAndRegisterServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }


    // =============================== User registration =================================== //

    /**
     * Register a user via email and send verification code.
     *
     * @param email user email
     * @author gaotianchi
     * @since 2024/12/14 18:20
     **/
    @Override
    public void registerUserViaEmailAndSendVerificationCode(String email, String password) {


        // 1. Try to insert user into database.
        int verificationCode = generateVerificationCode();
        String username = generateUsernameWithEmail(email);
        String encodedPassword = passwordEncoder.encode(password);
        User user = User.builder()
                .email(email)
                .password(encodedPassword)
                .username(username)
                .verificationCode(verificationCode)
                .verificationCodeExpiration(LocalDateTime.now().plus(Duration.ofMinutes(10)))
                .build();
        int rows = userDao.insertUser(user);
        if (rows != 1) {
            throw new SQLException(Code.SQL_INSERT_ERROR, "Fail to add new user.");
        }

        // 2. TODO: Send verification code to user's email.

    }

    @Override
    public void verifyEmail(String email, int verificationCode) {
        // 1. Load user from database.
        User user = userDao.selectByUsernameOrEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // 2. Check if verification code is correct.
        if (user.getVerificationCode().equals(verificationCode)) {
            throw new RuntimeException("Verification code is incorrect");
        }

        // 3. Verify whether the verification code has expired
        LocalDateTime currentTime = LocalDateTime.now();
        if (user.getVerificationCodeExpiration().isBefore(currentTime)) {
            throw new RuntimeException("Verification code has expired");
        }

        // 4. Now user's email is verified, so we can update email's status.
        int rows = userDao.updateUserById(User.builder().id(user.getId()).email_is_activated(1).build());
        if (rows != 1) {
            throw new SQLException(Code.SQL_UPDATE_ERROR, "Fail to verify email.");
        }
    }

    // =================================================== deregister ================================================ //

    @Override
    public void deregisterUserViaEmailAndSendVerificationCode(String email) {
        // 1. Load user from database.
        User user = userDao.selectByUsernameOrEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // 2. Update user's verification code.
        int verificationCode = generateVerificationCode();
        user.setVerificationCode(verificationCode);
        user.setVerificationCodeExpiration(LocalDateTime.now().plus(Duration.ofMinutes(10)));
        int rows = userDao.updateUserById(user);
        if (rows != 1) {
            throw new SQLException(Code.SQL_UPDATE_ERROR, "Fail to update user's verification code.");
        }

        // TODO: 3. Send verification code to user's email.
    }


    @Override
    public void confirmDeregisterUserViaEmailAndVerificationCode(String email, int verificationCode) {
        // 1. Load user from database.
        User user = userDao.selectByUsernameOrEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // 2. Check if verification code is correct.
        if (user.getVerificationCode().equals(verificationCode)) {
            throw new RuntimeException("Verification code is incorrect");
        }

        // 3. Verify whether the verification code has expired
        LocalDateTime currentTime = LocalDateTime.now();
        if (user.getVerificationCodeExpiration().isBefore(currentTime)) {
            throw new RuntimeException("Verification code has expired");
        }

        // 4. Set user's status to deregistered.
        int rows = userDao.updateUserById(User.builder().id(user.getId()).isEnabled(0).build());
        if (rows != 1) {
            throw new SQLException(Code.SQL_UPDATE_ERROR, "Fail to deregister user.");
        }

    }


    // =============================== User login =================================== //

    /**
     * Load user by username or email, used by Spring Security.
     * @param usernameOrEmail username or email
     * @return UserDetails
     * @throws UsernameNotFoundException if user not found
     */
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userDao.selectByUsernameOrEmail(usernameOrEmail);
        if (user == null) {
            throw new UsernameNotFoundException(usernameOrEmail);
        }
        Set<String> grantedAuthoritiesNames = userDao.selectUserRolesAndPermissionsNamesByUsernameOrEmail(usernameOrEmail);
        Set<GrantedAuthority> grantedAuthorities = grantedAuthoritiesNames.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        return UserDetailsImpl.builder()
                .authorities(grantedAuthorities)
                .user(user)
                .build();
    }

    @Builder
    @Data
    private static class UserDetailsImpl implements UserDetails {

        private User user;
        private Collection<? extends GrantedAuthority> authorities;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public String getUsername() {
            return user.getUsername();
        }

        @Override
        public boolean isAccountNonExpired() {
            return UserDetails.super.isAccountNonExpired();
        }

        @Override
        public boolean isAccountNonLocked() {
            return user.getIsLocked() == 0;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return UserDetails.super.isCredentialsNonExpired();
        }

        @Override
        public boolean isEnabled() {
            return user.getIsEnabled() == 1;
        }
    }

    private String generateUsernameWithEmail(String email) {
        String username = email.split("@")[0];
        int i = 1;
        while (true) {
            User user = userDao.selectByUsernameOrEmail(username);
            if (user == null) {
                return username;
            }
            username = email.split("@")[0] + "_" + i++;
        }
    }
}
