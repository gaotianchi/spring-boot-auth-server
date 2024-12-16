package com.gaotianchi.auth.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 用户表(User)实体类
 *
 * @author gaotianchi
 * @since 2024-12-07 11:24:29
 */
@Data
@Builder
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String username;
    private String password;
    private String email;
    private Date createdAt;
    private Date updatedAt;
    private Date lastLoginTime;
    private String lastLoginIp;
    private Integer failedAttempts;
    private Integer isLocked;
    private Date lockExpiration;
    private Integer isEnabled;
    private Integer verificationCode;
    private LocalDateTime verificationCodeExpiration;
    private Integer email_is_activated;
}

