package com.gaotianchi.auth.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户行为日志(ActionLog)实体类
 *
 * @author gaotianchi
 * @since 2024-11-27 21:01:31
 */
@Data
@Builder
public class ActionLog implements Serializable {

    @Serial
    private static final long serialVersionUID = -47114859349961305L;

    private Integer id;  // 自增ID
    private Integer userId;  // 用户ID
    private String action;  // 操作类型
    private Date actionTime;  // 操作时间
    private String objectType;  // 被操作的对象类型
    private Integer objectId;  // 被操作对象的唯一标识符
    private String ipAddress;  // 用户操作的IP地址
    private String deviceInfo;  // 用户操作设备的信息
    private String details;  // 操作的详细细节
    private Date createdAt;  // 日志记录的创建时间
}

