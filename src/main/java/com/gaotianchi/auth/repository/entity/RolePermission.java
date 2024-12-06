package com.gaotianchi.auth.repository.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 角色权限表(RolePermission)实体类
 *
 * @author gaotianchi
 * @since 2024-12-06 19:26:32
 */
@Data
@Builder
public class RolePermission implements

        Serializable {

    @Serial
    private static final long serialVersionUID = -74803780628427698L;

    private Integer id;
    private Integer roleCode;  // 角色代码
    private Integer permissionCode;  // 权限代码
}

