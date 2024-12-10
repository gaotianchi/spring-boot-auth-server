package com.gaotianchi.auth.base.converter;

import com.gaotianchi.auth.base.dto.UserRoleDTO;
import com.gaotianchi.auth.base.entity.UserRole;
import com.gaotianchi.auth.base.vo.UserRoleVO;
import org.springframework.stereotype.Component;

@Component
public class UserRoleConverter {

    public UserRole toEntity(UserRoleDTO dto) {
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