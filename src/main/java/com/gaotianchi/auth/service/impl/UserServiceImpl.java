package com.gaotianchi.auth.service.impl;

import com.gaotianchi.auth.dao.UserDao;
import com.gaotianchi.auth.entity.User;
import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.exception.SQLException;
import com.gaotianchi.auth.service.UserService;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 用户表(User)表服务实现类
 *
 * @author gaotianchi
 * @since 2024-11-28 20:45:39
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void insert(User user) {
        if (userDao.insert(user) != 1) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void deleteById(Integer id) {
        if (userDao.deleteById(id) != 1) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void update(User user) {
        if (userDao.update(user) != 1) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public User findById(Integer id) {
        return userDao.selectById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.selectByUsernameOrEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        Set<String> grantedAuthoritiesNames = userDao.selectUserRolesAndPermissionsNamesByUsername(username);
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
}
