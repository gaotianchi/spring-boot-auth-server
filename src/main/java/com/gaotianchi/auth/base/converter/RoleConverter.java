package com.gaotianchi.auth.base.converter;

import com.gaotianchi.auth.base.dto.RoleDTO;
import com.gaotianchi.auth.base.entity.Role;
import com.gaotianchi.auth.base.vo.RoleVO;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {

    public Role toEntity(RoleDTO dto) {
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