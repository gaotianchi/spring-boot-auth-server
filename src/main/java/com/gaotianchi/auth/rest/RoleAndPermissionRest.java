package com.gaotianchi.auth.rest;

import com.gaotianchi.auth.converter.UserRolePermissionConverter;
import com.gaotianchi.auth.dto.UserRolePermissionDTO;
import com.gaotianchi.auth.entity.RolePermission;
import com.gaotianchi.auth.entity.UserRole;
import com.gaotianchi.auth.service.RoleAndPermissionService;
import com.gaotianchi.auth.vo.PermissionVO;
import com.gaotianchi.auth.vo.RoleVO;
import com.gaotianchi.auth.vo.VO;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gaotianchi
 * @since 2024/12/18 16:40
 **/
@RestController
@RequestMapping("roles-and-permissions")
public class RoleAndPermissionRest {

    private final RoleAndPermissionService roleAndPermissionService;
    private final UserRolePermissionConverter userRolePermissionConverter;

    public RoleAndPermissionRest(RoleAndPermissionService roleAndPermissionService, UserRolePermissionConverter userRolePermissionConverter) {
        this.roleAndPermissionService = roleAndPermissionService;
        this.userRolePermissionConverter = userRolePermissionConverter;
    }

    @PutMapping("user-roles")
    public VO<Void> handleUpdateUserRolesRequest(
            @Validated(UserRolePermissionDTO.UpdateUserRole.class)
            @RequestBody
            List<UserRolePermissionDTO> userRoleDTOs
    ) {
        List<UserRole> userRoles = userRoleDTOs.stream()
                .map(userRolePermissionConverter::toUserRole)
                .toList();
        roleAndPermissionService.updateUserRoles(userRoles);
        return VO.success(null);
    }

    @PutMapping("role-permissions")
    public VO<Void> handleUpdateRolePermissionsRequest(
            @Validated(UserRolePermissionDTO.UpdateRolePermission.class)
            @RequestBody
            List<UserRolePermissionDTO> rolePermissionDTOs
    ) {
        List<RolePermission> rolePermissions = rolePermissionDTOs.stream()
                .map(userRolePermissionConverter::toRolePermission)
                .toList();
        roleAndPermissionService.updateRolePermissions(rolePermissions);
        return VO.success(null);
    }

    @GetMapping("all-roles-by-user-id")
    public VO<List<RoleVO>> handleGetAllRolesByUserIdRequest(
            @RequestParam("userId")
            @NotNull(message = "User id cannot be null.")
            int userId
    ) {
        List<RoleVO> roleVOs = roleAndPermissionService.getAllRolesByUserId(userId)
                .stream()
                .map(userRolePermissionConverter::toRoleVO)
                .toList();
        return VO.success(roleVOs);
    }

    @GetMapping("all-permissions-by-role-id")
    public VO<List<PermissionVO>> handleGetAllPermissionsByRoleIdRequest(
            @RequestParam("roleId")
            @NotNull(message = "Role id cannot be null.")
            int roleId
    ) {
        List<PermissionVO> permissionVOS = roleAndPermissionService.getAllPermissionsByRoleId(roleId)
                .stream()
                .map(userRolePermissionConverter::toPermissionVO)
                .toList();
        return VO.success(permissionVOS);
    }
}
