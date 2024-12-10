package com.gaotianchi.auth.base.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class PermissionDTO implements Serializable {
    private Integer id;

    @NotNull(groups = {CreatePermission.class})
    private Integer code;

    @NotNull(groups = {CreatePermission.class})
    private String name;

    private String description;
    private Date createdAt;
    private Date updatedAt;

    public interface CreatePermission {
    }

    public interface UpdatePermission {
    }
}