package com.gaotianchi.auth.base.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class RolePermissionDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // =========================== Basic information ================================= //

    private Integer id;

    @NotNull(groups = {CreateRolePermission.class})
    private Integer roleCode;

    @NotNull(groups = {CreateRolePermission.class})
    private Integer permissionCode;

    // =========================== Advanced information ================================= //

    public interface CreateRolePermission {
    }

    public interface UpdateRolePermission {
    }
}