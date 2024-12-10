package com.gaotianchi.auth.base.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RolePermissionDTO implements Serializable {
    private Integer id;

    @NotNull(groups = {CreateRolePermission.class})
    private Integer roleCode;

    @NotNull(groups = {CreateRolePermission.class})
    private Integer permissionCode;

    public interface CreateRolePermission {
    }

    public interface UpdateRolePermission {
    }
}