package com.gaotianchi.auth.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author gaotianchi
 * @since 2024/12/19 8:59
 **/
@Data
@Builder
public class UserRolePermissionDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotNull(groups = {UpdateUserRole.class}, message = "User id cannot be null.")
    private Integer userId;

    @NotNull(groups = {UpdateUserRole.class, UpdateRolePermission.class}, message = "Role id cannot be null.")
    private Integer roleCode;

    @NotNull(groups = {UpdateRolePermission.class}, message = "Permission id cannot be null.")
    private Integer permissionCode;

    // ============================== Validation groups ================================ //

    public interface UpdateUserRole extends Default {
    }

    public interface UpdateRolePermission extends Default {
    }
}
