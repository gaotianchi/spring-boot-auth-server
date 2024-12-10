package com.gaotianchi.auth.base.converter;

import com.gaotianchi.auth.base.dto.UserDTO;
import com.gaotianchi.auth.base.entity.User;
import com.gaotianchi.auth.base.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserConverter {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "lastLoginTime", ignore = true)
    @Mapping(target = "lastLoginIp", ignore = true)
    @Mapping(target = "failedAttempts", ignore = true)
    @Mapping(target = "isLocked", ignore = true)
    @Mapping(target = "lockExpiration", ignore = true)
    @Mapping(target = "isEnabled", ignore = true)
    User toEntity(UserDTO userDto);

    UserDTO toDTO(User user);

    UserVO toVO(User user);
}