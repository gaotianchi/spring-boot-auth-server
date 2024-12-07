package com.gaotianchi.auth.converter;

import com.gaotianchi.auth.dto.UserRoleDto;
import com.gaotianchi.auth.infrastructure.entity.UserRole;
import com.gaotianchi.auth.vo.UserRoleVO;
import org.springframework.stereotype.Component;

@Component
public class UserRoleConverter {

    public UserRole toEntity(UserRoleDto dto) {
        return UserRole.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .roleCode(dto.getRoleCode())
                .build();
    }

    public UserRoleVO toVO(UserRole entity) {
        return UserRoleVO.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .roleCode(entity.getRoleCode())
                .build();
    }
}