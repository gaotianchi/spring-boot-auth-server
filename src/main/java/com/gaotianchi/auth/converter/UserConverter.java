package com.gaotianchi.auth.converter;

import com.gaotianchi.auth.dto.UserDTO;
import com.gaotianchi.auth.entity.User;
import com.gaotianchi.auth.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author gaotianchi
 * @since 2024/12/18 16:27
 **/
@Mapper(componentModel = "spring")
public interface UserConverter {

    User toEntity(UserDTO userDTO);

    @Mapping(target = "roles", ignore = true)
    UserVO toVO(User user);
}
