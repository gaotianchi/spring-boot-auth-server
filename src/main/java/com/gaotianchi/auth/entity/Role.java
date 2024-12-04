package com.gaotianchi.auth.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 角色表(Role)实体类
 *
 * @author gaotianchi
 * @since 2024-11-27 21:01:31
 */
@Data
@Builder
public class Role implements Serializable {

    @Serial
    private static final long serialVersionUID = 191064317511591012L;

    private Integer id;  // 自增ID
    private Integer code;  // 角色代码
    private String name;  // 角色名称
    private String description;  // 描述
    private Date createdAt;  // 创建时间
    private Date updatedAt;  // 更新时间
}

