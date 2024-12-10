package com.gaotianchi.auth.base.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * (UserRole)实体类
 *
 * @author gaotianchi
 * @since 2024-12-07 11:24:29
 */
@Data
@Builder
public class UserRole implements Serializable {

    @Serial
    private static final long serialVersionUID = 915142361758013020L;

    private Integer id;
    private Integer userId;  // 用户id
    private Integer roleCode;  // 角色代码
}

