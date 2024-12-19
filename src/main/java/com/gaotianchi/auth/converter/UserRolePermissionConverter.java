package com.gaotianchi.auth.converter;

import com.gaotianchi.auth.dto.UserRolePermissionDTO;
import com.gaotianchi.auth.entity.Permission;
import com.gaotianchi.auth.entity.Role;
import com.gaotianchi.auth.entity.RolePermission;
import com.gaotianchi.auth.entity.UserRole;
import com.gaotianchi.auth.vo.PermissionVO;
import com.gaotianchi.auth.vo.RoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author gaotianchi
 * @since 2024/12/19 9:08
 **/
@Mapper(componentModel = "spring")
public interface UserRolePermissionConverter {

    UserRole toUserRole(UserRolePermissionDTO userRolePermissionDTO);

    RolePermission toRolePermission(UserRolePermissionDTO userRolePermissionDTO);

    @Mapping(target = "permissions", ignore = true)
    RoleVO toRoleVO(Role role);

    PermissionVO toPermissionVO(Permission permission);
}
