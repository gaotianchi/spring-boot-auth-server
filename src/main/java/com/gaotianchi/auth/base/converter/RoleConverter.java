package com.gaotianchi.auth.base.converter;

import com.gaotianchi.auth.base.dto.RoleDTO;
import com.gaotianchi.auth.base.entity.Role;
import com.gaotianchi.auth.base.vo.RoleVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleConverter {

    Role toEntity(RoleDTO dto);

    RoleVO toVO(Role entity);
}