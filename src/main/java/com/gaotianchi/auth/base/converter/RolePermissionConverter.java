package com.gaotianchi.auth.base.converter;

import com.gaotianchi.auth.base.dto.RolePermissionDTO;
import com.gaotianchi.auth.base.entity.RolePermission;
import com.gaotianchi.auth.base.vo.RolePermissionVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolePermissionConverter {

    RolePermission toEntity(RolePermissionDTO dto);

    RolePermissionVO toVO(RolePermission entity);
}