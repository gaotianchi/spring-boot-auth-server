package com.gaotianchi.auth.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class ActionLogDto implements Serializable {
    private Integer id;

    @NotNull(groups = {CreateActionLog.class})
    private Integer userId;

    @NotNull(groups = {CreateActionLog.class})
    private String action;

    private Date actionTime;
    private String objectType;
    private Integer objectId;
    private String ipAddress;
    private String deviceInfo;
    private String details;
    private Date createdAt;

    public interface CreateActionLog {
    }
}