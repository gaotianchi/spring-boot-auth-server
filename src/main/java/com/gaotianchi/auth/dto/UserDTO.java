package com.gaotianchi.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

/**
 * @author gaotianchi
 * @since 2024/12/18 13:50
 **/
@Data
@Builder
public class UserDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // ============================= Basic information ============================= //
    private Integer id;
    private String username;

    @NotNull(groups = {RegisterUser.class, VerifyEmail.class, CheckEmail.class}, message = "Email cannot be empty")
    @Email(groups = {RegisterUser.class, VerifyEmail.class, CheckEmail.class}, message = "Invalid email format")
    private String email;

    @NotNull(groups = {RegisterUser.class, UpdatePassword.class, ResetPassword.class}, message = "Password cannot be empty")
    @Pattern(groups = {RegisterUser.class, UpdatePassword.class, ResetPassword.class}, regexp = "^[a-zA-Z][a-zA-Z0-9]{7,}$", message = "The password must start with a letter, contain letters and numbers, and be at least 8 characters long.")
    private String password;

    // ============================= Date related =================================== //
    private Date createdAt;
    private Date updatedAt;
    private Date lockExpiration;
    private Date lastLoginTime;
    private Instant verificationCodeExpiration;

    // =============================== State related ================================= //
    private Integer isLocked;
    private Integer isEnabled;
    private Integer emailIsVerified;

    // =============================== Others ========================================= //
    @NotNull(groups = {VerifyEmail.class, ConfirmDeregisterUser.class, ResetPassword.class}, message = "Verification code cannot be empty")
    @Pattern(groups = {VerifyEmail.class, ConfirmDeregisterUser.class, ResetPassword.class}, regexp = "^d{4}$", message = "Verification code must be 4 digits")
    private Integer verificationCode;
    private String lastLoginIp;
    private Integer failedAttempts;

    @NotNull(groups = {UpdatePassword.class, ResetPassword.class}, message = "New password cannot be empty")
    @Pattern(groups = {UpdatePassword.class, ResetPassword.class}, regexp = "^[a-zA-Z][a-zA-Z0-9]{7,}$", message = "New password must start with a letter, contain letters and numbers, and be at least 8 characters long.")
    private String newPassword;

    // =============================== Verification groups =================================== //
    public interface RegisterUser extends Default {
    }

    public interface VerifyEmail extends Default {
    }

    public interface ConfirmDeregisterUser extends Default {
    }

    public interface UpdatePassword extends Default {
    }

    public interface CheckEmail extends Default {
    }

    public interface ResetPassword extends Default {
    }
}
