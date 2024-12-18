package com.gaotianchi.auth.dto;

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

    @NotNull(groups = {RegisterUser.class}, message = "Email cannot be empty")
    private String email;

    @NotNull(groups = {RegisterUser.class}, message = "Password cannot be empty")
    @Pattern(groups = {RegisterUser.class}, regexp = "^[a-zA-Z][a-zA-Z0-9]{7,}$", message = "The password must start with a letter, contain letters and numbers, and be at least 8 characters long.")
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
    private Integer verificationCode;
    private String lastLoginIp;
    private Integer failedAttempts;

    // =============================== Verification groups =================================== //
    public interface RegisterUser extends Default {
    }
}
