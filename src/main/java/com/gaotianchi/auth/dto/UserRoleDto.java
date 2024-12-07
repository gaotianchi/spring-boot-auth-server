package com.gaotianchi.auth.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UserRoleDto implements Serializable {
    private Integer id;

    @NotNull(groups = {CreateUserRole.class})
    private Integer userId;

    @NotNull(groups = {CreateUserRole.class})
    private Integer roleCode;

    public interface CreateUserRole {
    }
}