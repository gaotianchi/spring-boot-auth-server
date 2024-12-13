package com.gaotianchi.auth.base.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class UserRoleDTO implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    // ============================ Basic information =========================== //

    private Integer id;

    @NotNull(groups = {CreateUserRole.class})
    private Integer userId;

    @NotNull(groups = {CreateUserRole.class})
    private Integer roleCode;

    // ============================== Validation groups =========================== //

    public interface CreateUserRole {
    }
}