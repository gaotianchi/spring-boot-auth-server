package com.gaotianchi.auth.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class RoleDto implements Serializable {
    private Integer id;

    @NotNull(groups = {CreateRole.class})
    private Integer code;

    @NotNull(groups = {CreateRole.class})
    private String name;

    private String description;
    private Date createdAt;
    private Date updatedAt;

    public interface CreateRole {
    }

    public interface UpdateRole {
    }
}