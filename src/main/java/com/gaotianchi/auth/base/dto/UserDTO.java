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
 * @author gaotianchi
 * @since 2024/12/13
 */
@Data
@Builder
public class UserDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // ====================== Basic information ====================== //

    private Integer id;

    @NotBlank(message = "Username cannot be empty.")
    @Size(max = 255, message = "Username length cannot exceed 255")
    private String username;

    @NotBlank(message = "Password cannot be empty.")
    @Size(max = 255, message = "Password length cannot exceed 255")
    private String password;

    @Email(message = "The email format is incorrect")
    @Size(max = 255, message = "Email length cannot exceed 255")
    private String email;

    // ============================ Time related ============================ //

    private Date passwordExpiration;

    private Date createdAt;

    private Date updatedAt;

    private Date lastLoginTime;

    // ============================ Additional information ============================ //

    @Size(max = 45, message = "IP address length cannot exceed 255")
    private String lastLoginIp;

    @NotNull
    private Integer failedAttempts;

    @NotNull
    private Integer isLocked;

    private Date lockExpiration;

    @NotNull
    private Integer isEnabled;

    // ============================== Validation group ============================== //

    public interface CreateUser {
    }

    public interface UpdateUser {
    }
}