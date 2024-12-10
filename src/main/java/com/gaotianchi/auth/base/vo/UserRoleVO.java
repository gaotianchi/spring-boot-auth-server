package com.gaotianchi.auth.base.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRoleVO {
    private Integer id;
    private Integer userId;
    private Integer roleCode;
}