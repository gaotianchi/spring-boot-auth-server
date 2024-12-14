package com.gaotianchi.auth.base.converter;

import com.gaotianchi.auth.base.dto.PermissionDTO;
import com.gaotianchi.auth.base.entity.Permission;
import com.gaotianchi.auth.base.vo.PermissionVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionConverter {

    Permission toEntity(PermissionDTO dto);

    PermissionVO toVO(Permission entity);
}