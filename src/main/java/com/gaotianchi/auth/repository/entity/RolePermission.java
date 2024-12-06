package com.gaotianchi.auth.repository.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 角色权限表(RolePermission)实体类
 *
 * @author gaotianchi
 * @since 2024-12-06 19:47:32
 */
@Data
@Builder
public class RolePermission implements Serializable {

    @Serial
    private static final long serialVersionUID = 481858847911235247L;

    private Integer id;
    private Integer roleCode;  // 角色代码
    private Integer permissionCode;  // 权限代码
}

