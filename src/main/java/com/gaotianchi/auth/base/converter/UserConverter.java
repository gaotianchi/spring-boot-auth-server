package com.gaotianchi.auth.base.converter;

import com.gaotianchi.auth.base.dto.UserDTO;
import com.gaotianchi.auth.base.entity.User;
import com.gaotianchi.auth.base.vo.UserVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConverter {

    User toEntity(UserDTO userDto);

    UserVO toVO(User user);
}