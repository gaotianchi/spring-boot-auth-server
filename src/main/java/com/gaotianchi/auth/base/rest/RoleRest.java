package com.gaotianchi.auth.base.rest;

import com.gaotianchi.auth.base.converter.RoleConverter;
import com.gaotianchi.auth.base.dto.RoleDTO;
import com.gaotianchi.auth.base.entity.Role;
import com.gaotianchi.auth.base.service.RoleBaseService;
import com.gaotianchi.auth.base.vo.RoleVO;
import com.gaotianchi.auth.base.vo.VO;
import com.gaotianchi.auth.enums.Code;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gaotianchi.auth.utils.RestTool.getPageRequest;

/**
 * @author gaotianchi
 * @since 2024/12/7 17:28
 **/
@RestController
@RequestMapping("role")
public class RoleRest {

    private final RoleBaseService roleService;
    private final RoleConverter roleConverter;

    public RoleRest(RoleBaseService roleService, RoleConverter roleConverter) {
        this.roleService = roleService;
        this.roleConverter = roleConverter;
    }

    @PostMapping("")
    public VO<String> handleAddNewRoleRequest(
            @RequestBody
            @Validated(RoleDTO.CreateRole.class)
            RoleDTO roleDto
    ) {
        Role role = roleConverter.toEntity(roleDto);
        roleService.addNewRole(role);
        return VO.response(Code.SUCCESS, "/role/info/" + role.getId());
    }

    @PostMapping("batch")
    public VO<String> handleAddNewRolesInBatchesRequest(
            @RequestBody
            @Validated(RoleDTO.CreateRole.class)
            List<RoleDTO> roleDto
    ) {
        List<Role> roles = roleDto
                .stream()
                .map(roleConverter::toEntity)
                .toList();
        roleService.addNewRolesInBatches(roles);
        return VO.response(Code.SUCCESS, roles
                .stream()
                .map(role -> "/role/info/" + role.getId())
                .toList()
                .toString());
    }

    @DeleteMapping("")
    public VO<Void> handleRemoveRoleByIdRequest(
            @RequestParam("id")
            @NotNull(message = "id cannot be null.")
            @Min(value = 1, message = "id must be greater than or equal to 1")
            Integer id
    ) {
        roleService.removeRoleById(id);
        return VO.response(Code.SUCCESS, null);
    }

    @DeleteMapping("batch")
    public VO<Void> handleRemoveRolesInBatchesByIdsRequest(
            @RequestParam("id")
            @NotNull(message = "ids cannot be null.")
            List<Integer> ids
    ) {
        roleService.removeRolesInBatchesByIds(ids);
        return VO.response(Code.SUCCESS, null);
    }

    @PutMapping("")
    public VO<String> handleUpdateRoleByIdRequest(
            @RequestBody
            @Validated(RoleDTO.UpdateRole.class)
            RoleDTO roleDto
    ) {
        Role role = roleConverter.toEntity(roleDto);
        roleService.updateRoleById(role);
        return VO.response(Code.SUCCESS, "/role/info/" + role.getId());
    }

    @PutMapping("batch")
    public VO<String> handleAddNewOrUpdateExistingRolesInBatchesRequest(
            @RequestBody
            @Validated(RoleDTO.UpdateRole.class)
            List<RoleDTO> roleDTOList
    ) {
        List<Role> roles = roleDTOList
                .stream()
                .map(roleConverter::toEntity)
                .toList();
        roleService.addNewOrUpdateExistingRolesInBatches(roles);
        return VO.response(Code.SUCCESS, roles
                .stream()
                .map(role -> "/role/info/" + role.getId())
                .toList()
                .toString());
    }

    @GetMapping("info")
    public VO<RoleVO> handleGetRoleByIdRequest(
            @RequestParam
            @NotNull(message = "id cannot be null")
            @Min(value = 1, message = "id must be greater than or equal to 1")
            Integer id
    ) {
        Role role = roleService.getRoleById(id);
        RoleVO roleDto = roleConverter.toVO(role);
        return VO.response(Code.SUCCESS, roleDto);
    }

    @GetMapping("info-list")
    public VO<Iterable<RoleVO>> handleGetRoleByPageRequest(
            @ModelAttribute RoleDTO roleDto,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "id:asc") List<String> sorts
    ) {
        Role role = roleConverter.toEntity(roleDto);
        PageRequest pageRequest = getPageRequest(page, size, sorts);
        Page<Role> rolePage = roleService.getRolesByPage(role, pageRequest);
        Page<RoleVO> roleVOPage = rolePage.map(roleConverter::toVO);
        return VO.response(Code.SUCCESS, roleVOPage);
    }
}
