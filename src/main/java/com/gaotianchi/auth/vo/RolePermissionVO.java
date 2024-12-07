package com.gaotianchi.auth.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolePermissionVO {
    private Integer id;
    private Integer roleCode;
    private Integer permissionCode;
}