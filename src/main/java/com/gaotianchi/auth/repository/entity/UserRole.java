package com.gaotianchi.auth.repository.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * (UserRole)实体类
 *
 * @author gaotianchi
 * @since 2024-12-06 19:47:32
 */
@Data
@Builder
public class UserRole implements Serializable {

    @Serial
    private static final long serialVersionUID = -66854490142512496L;

    private Integer id;
    private Integer userId;  // 用户id
    private Integer roleCode;  // 角色代码
}

