package com.gaotianchi.auth.base.converter;

import com.gaotianchi.auth.base.dto.RolePermissionDTO;
import com.gaotianchi.auth.base.entity.RolePermission;
import com.gaotianchi.auth.base.vo.RolePermissionVO;
import org.springframework.stereotype.Component;

@Component
public class RolePermissionConverter {

    public RolePermission toEntity(RolePermissionDTO dto) {
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