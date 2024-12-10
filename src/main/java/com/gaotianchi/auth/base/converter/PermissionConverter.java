package com.gaotianchi.auth.base.converter;

import com.gaotianchi.auth.base.dto.PermissionDTO;
import com.gaotianchi.auth.base.entity.Permission;
import com.gaotianchi.auth.base.vo.PermissionVO;
import org.springframework.stereotype.Component;

@Component
public class PermissionConverter {

    public Permission toEntity(PermissionDTO dto) {
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