package com.gaotianchi.auth.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author gaotianchi
 * @since 2024/11/29 12:50
 **/
@Data
@Builder
public class UserVo {

    private Integer id;
    private String username;
    private String email;
    private Date createdAt;
    private Date updatedAt;
    private Date lastLoginTime;
    private String lastLoginIp;
    private Integer failedAttempts;
    private Integer isLocked;  // 是否锁定用户（0未锁定，1锁定）
    private Date lockExpiration;
    private Integer isEnabled;  // 账户是否启用（0启用，1未启用）
}
