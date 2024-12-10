package com.gaotianchi.auth.base.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * Data Transfer Object for User entity
 */
@Data
@Builder
public class UserDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotBlank(message = "用户名不能为空")
    @Size(max = 255, message = "用户名长度不能超过255")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(max = 255, message = "密码长度不能超过255")
    private String password;

    @Email(message = "邮箱格式不正确")
    @Size(max = 255, message = "邮箱长度不能超过255")
    private String email;

    private Date createdAt;

    private Date updatedAt;

    private Date lastLoginTime;

    @Size(max = 45, message = "IP地址长度不能超过45")
    private String lastLoginIp;

    @NotNull
    private Integer failedAttempts;

    @NotNull
    private Integer isLocked;

    private Date lockExpiration;

    @NotNull
    private Integer isEnabled;

    public interface CreateUser {
    }

    public interface UpdateUser {
    }
}