package com.gaotianchi.auth.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RolePermissionDto implements Serializable {
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