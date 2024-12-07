package com.gaotianchi.auth.converter;

import com.gaotianchi.auth.dto.RoleDto;
import com.gaotianchi.auth.infrastructure.entity.Role;
import com.gaotianchi.auth.vo.RoleVO;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {

    public Role toEntity(RoleDto dto) {
        return Role.builder()
                .id(dto.getId())
                .code(dto.getCode())
                .name(dto.getName())
                .description(dto.getDescription())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }

    public RoleVO toVO(Role entity) {
        return RoleVO.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}