package com.gaotianchi.auth.repository.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 权限表(Permission)实体类
 *
 * @author gaotianchi
 * @since 2024-12-06 19:47:32
 */
@Data
@Builder
public class Permission implements Serializable {

    @Serial
    private static final long serialVersionUID = -45210136263239561L;

    private Integer id;  // 自增ID
    private Integer code;  // 权限代码
    private String name;  // 权限名称
    private String description;  // 描述
    private Date createdAt;
    private Date updatedAt;
}

