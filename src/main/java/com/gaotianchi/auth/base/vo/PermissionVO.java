package com.gaotianchi.auth.base.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PermissionVO {
    private Integer id;
    private Integer code;
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;
}