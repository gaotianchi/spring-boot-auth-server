package com.gaotianchi.auth.base.converter;

import com.gaotianchi.auth.base.dto.UserRoleDTO;
import com.gaotianchi.auth.base.entity.UserRole;
import com.gaotianchi.auth.base.vo.UserRoleVO;
import org.mapstruct.Mapper;

@Mapper
public interface UserRoleConverter {

    UserRole toEntity(UserRoleDTO dto);

    UserRoleVO toVO(UserRole entity);
}