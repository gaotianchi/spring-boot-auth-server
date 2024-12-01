package com.gaotianchi.auth.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 权限表(Permission)实体类
 *
 * @author gaotianchi
 * @since 2024-11-27 21:01:31
 */
@Data
@Builder
public class Permission implements Serializable {

    @Serial
    private static final long serialVersionUID = -37027710868994924L;

    private Integer id;  // 自增ID
    private Integer code;  // 权限代码
    private String name;  // 权限名称
    private String description;  // 描述
    private Date createdAt;
    private Date updatedAt;
}

