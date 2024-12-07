package com.gaotianchi.auth.converter;

import com.gaotianchi.auth.dto.RolePermissionDto;
import com.gaotianchi.auth.infrastructure.entity.RolePermission;
import com.gaotianchi.auth.vo.RolePermissionVO;
import org.springframework.stereotype.Component;

@Component
public class RolePermissionConverter {

    public RolePermission toEntity(RolePermissionDto dto) {
        return RolePermission.builder()
                .id(dto.getId())
                .roleCode(dto.getRoleCode())
                .permissionCode(dto.getPermissionCode())
                .build();
    }

    public RolePermissionVO toVO(RolePermission entity) {
        return RolePermissionVO.builder()
                .id(entity.getId())
                .roleCode(entity.getRoleCode())
                .permissionCode(entity.getPermissionCode())
                .build();
    }
}