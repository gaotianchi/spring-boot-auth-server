package com.gaotianchi.auth.base.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ActionLogVO {
    private Integer id;
    private Integer userId;
    private String action;
    private Date actionTime;
    private String objectType;
    private Integer objectId;
    private String ipAddress;
    private String deviceInfo;
    private String details;
    private Date createdAt;
}