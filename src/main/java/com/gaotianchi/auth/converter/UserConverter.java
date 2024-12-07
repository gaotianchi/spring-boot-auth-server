package com.gaotianchi.auth.converter;

import com.gaotianchi.auth.dto.UserDto;
import com.gaotianchi.auth.infrastructure.entity.User;
import com.gaotianchi.auth.vo.UserVo;
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
    User toEntity(UserDto userDto);

    UserDto toDTO(User user);

    UserVo toVO(User user);
}