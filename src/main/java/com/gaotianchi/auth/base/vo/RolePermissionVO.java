package com.gaotianchi.auth.base.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolePermissionVO {
    private Integer id;
    private Integer roleCode;
    private Integer permissionCode;
}