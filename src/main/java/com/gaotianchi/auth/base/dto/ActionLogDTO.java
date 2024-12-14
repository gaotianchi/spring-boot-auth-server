package com.gaotianchi.auth.base.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class ActionLogDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // ============================= Basic information =============================== //

    private Integer id;

    @NotNull(groups = {CreateActionLog.class})
    private Integer userId;

    @NotNull(groups = {CreateActionLog.class})
    private String action;

    // =============================== Time related ====================================== //

    private Date actionTime;
    private Date createdAt;

    // ================================ Object related ===================================== //

    private String objectType;
    private Integer objectId;

    // =================================== Others =========================================== //

    private String ipAddress;
    private String deviceInfo;
    private String details;


    public interface CreateActionLog {
    }
}