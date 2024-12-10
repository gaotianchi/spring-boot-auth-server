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
    public VO<String> handleCreateRoleRequest(
            @RequestBody
            @Validated(RoleDTO.CreateRole.class)
            RoleDTO roleDto
    ) {
        Role role = roleConverter.toEntity(roleDto);
        roleService.addNewRole(role);
        return VO.response(Code.SUCCESS, "/role/info/" + role.getId());
    }

    @PostMapping("batch")
    public VO<String> handleCreateRolesBatchRequest(
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
            @NotNull(message = "id 不能为空")
            @Min(value = 1, message = "id 必须大于等于 1")
            Integer id
    ) {
        roleService.removeRoleById(id);
        return VO.response(Code.SUCCESS, null);
    }

    @DeleteMapping("batch")
    public VO<Void> handleRemoveRolesBatchByIdsRequest(
            @RequestParam("ids")
            @NotNull(message = "ids 不能为空")
            List<Integer> ids
    ) {
        roleService.removeRolesInBatchesByIds(ids);
        return VO.response(Code.SUCCESS, null);
    }

    @PutMapping("")
    public VO<String> handleUpdateRoleRequest(
            @RequestBody
            @Validated(RoleDTO.UpdateRole.class)
            RoleDTO roleDto
    ) {
        Role role = roleConverter.toEntity(roleDto);
        roleService.updateRoleById(role);
        return VO.response(Code.SUCCESS, "/role/info/" + role.getId());
    }

    @PutMapping("batch")
    public VO<String> handleUpdateRolesBatchRequest(
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

    @GetMapping("info/{id}")
    public VO<RoleVO> handleGetRoleByIdRequest(
            @PathVariable
            @NotNull(message = "id 不能为空")
            @Min(value = 1, message = "id 必须大于等于 1")
            Integer id
    ) {
        Role role = roleService.getRoleById(id);
        RoleVO roleDto = roleConverter.toVO(role);
        return VO.response(Code.SUCCESS, roleDto);
    }

    @GetMapping("info-list")
    public VO<Iterable<RoleVO>> handleGetRoleListRequest(
            @ModelAttribute RoleDTO roleDto,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Role role = roleConverter.toEntity(roleDto);
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Role> rolePage = roleService.getRolesByPage(role, pageRequest);
        Page<RoleVO> roleVOPage = rolePage.map(roleConverter::toVO);
        return VO.response(Code.SUCCESS, roleVOPage);
    }
}
