package com.gaotianchi.auth.converter;

import com.gaotianchi.auth.dto.PermissionDto;
import com.gaotianchi.auth.infrastructure.entity.Permission;
import com.gaotianchi.auth.vo.PermissionVO;
import org.springframework.stereotype.Component;

@Component
public class PermissionConverter {

    public Permission toEntity(PermissionDto dto) {
        return Permission.builder()
                .id(dto.getId())
                .code(dto.getCode())
                .name(dto.getName())
                .description(dto.getDescription())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }

    public PermissionVO toVO(Permission entity) {
        return PermissionVO.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}