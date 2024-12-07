package com.gaotianchi.auth.converter;

import com.gaotianchi.auth.dto.ActionLogDto;
import com.gaotianchi.auth.infrastructure.entity.ActionLog;
import com.gaotianchi.auth.vo.ActionLogVO;
import org.springframework.stereotype.Component;

@Component
public class ActionLogConverter {

    public ActionLog toEntity(ActionLogDto dto) {
        return ActionLog.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .action(dto.getAction())
                .actionTime(dto.getActionTime())
                .objectType(dto.getObjectType())
                .objectId(dto.getObjectId())
                .ipAddress(dto.getIpAddress())
                .deviceInfo(dto.getDeviceInfo())
                .details(dto.getDetails())
                .createdAt(dto.getCreatedAt())
                .build();
    }

    public ActionLogVO toVO(ActionLog entity) {
        return ActionLogVO.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .action(entity.getAction())
                .actionTime(entity.getActionTime())
                .objectType(entity.getObjectType())
                .objectId(entity.getObjectId())
                .ipAddress(entity.getIpAddress())
                .deviceInfo(entity.getDeviceInfo())
                .details(entity.getDetails())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}