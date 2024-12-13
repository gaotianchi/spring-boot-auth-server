package com.gaotianchi.auth.base.rest;

import com.gaotianchi.auth.base.converter.RolePermissionConverter;
import com.gaotianchi.auth.base.dto.RolePermissionDTO;
import com.gaotianchi.auth.base.entity.RolePermission;
import com.gaotianchi.auth.base.service.RolePermissionBaseService;
import com.gaotianchi.auth.base.vo.RolePermissionVO;
import com.gaotianchi.auth.base.vo.VO;
import com.gaotianchi.auth.enums.Code;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.gaotianchi.auth.utils.RestTool.getPageRequest;

/**
 * @author gaotianchi
 * @since 2024/12/7 18:06
 **/
@RestController
@RequestMapping("role-permission")
public class RolePermissionRest {

    private final RolePermissionBaseService rolePermissionBaseService;
    private final RolePermissionConverter rolePermissionConverter;

    public RolePermissionRest(RolePermissionBaseService rolePermissionBaseService, RolePermissionConverter rolePermissionConverter) {
        this.rolePermissionBaseService = rolePermissionBaseService;
        this.rolePermissionConverter = rolePermissionConverter;
    }

    @PostMapping("")
    public VO<String> handleAddNewRolePermissionRequest(
            @RequestBody
            @Validated(RolePermissionDTO.CreateRolePermission.class)
            RolePermissionDTO rolePermissionDto
    ) {
        RolePermission rolePermission = rolePermissionConverter.toEntity(rolePermissionDto);
        rolePermissionBaseService.addNewRolePermission(rolePermission);
        return VO.response(Code.SUCCESS, "/role-permission/info/" + rolePermission.getId());
    }

    @PostMapping("batch")
    public VO<String> handleAddNewRolePermissionInBatchesRequest(
            @RequestBody
            @Validated(RolePermissionDTO.CreateRolePermission.class)
            List<RolePermissionDTO> rolePermissionDTOList
    ) {
        List<RolePermission> rolePermissionList = rolePermissionDTOList
                .stream()
                .map(rolePermissionConverter::toEntity)
                .toList();
        rolePermissionBaseService.addNewRolePermissionsInBatches(rolePermissionList);
        List<String> uris = new ArrayList<>();
        rolePermissionList.forEach(rolePermission -> uris.add("/role-permission/info/" + rolePermission.getId()));
        return VO.response(Code.SUCCESS, uris.toString());
    }

    @DeleteMapping("")
    public VO<Void> handleRemoveRolePermissionByIdRequest(
            @RequestParam("id")
            @NotNull(message = "id cannot be null.")
            @Min(value = 1, message = "id must be greater than or equal to 1")
            Integer id
    ) {
        rolePermissionBaseService.removeRolePermissionById(id);
        return VO.response(Code.SUCCESS, null);
    }

    @DeleteMapping("batch")
    public VO<Void> handleRemoveRolePermissionInBatchesByIdsRequest(
            @RequestParam("id")
            @NotNull(message = "id cannot be null.")
            @Size(min = 1, message = "id length must be greater than or equal to 1")
            List<@NotNull(message = "id cannot be null") Integer> ids
    ) {
        rolePermissionBaseService.removeRolePermissionsInBatchesByIds(ids);
        return VO.response(Code.SUCCESS, null);
    }

    @PutMapping("")
    public VO<String> handleUpdateRolePermissionByIdRequest(
            @RequestBody
            @Validated(RolePermissionDTO.UpdateRolePermission.class)
            RolePermissionDTO rolePermissionDto
    ) {
        RolePermission rolePermission = rolePermissionConverter.toEntity(rolePermissionDto);
        rolePermissionBaseService.updateRolePermissionById(rolePermission);
        return VO.response(Code.SUCCESS, "/role-permission/info/" + rolePermission.getId());
    }

    @PutMapping("batch")
    public VO<String> handleAddNewOrUpdateExistingRolePermissionInBatchesRequest(
            @RequestBody
            @Validated(RolePermissionDTO.UpdateRolePermission.class)
            List<RolePermissionDTO> rolePermissionDTOList
    ) {
        List<RolePermission> rolePermissionList = rolePermissionDTOList
                .stream()
                .map(rolePermissionConverter::toEntity)
                .toList();
        rolePermissionBaseService.addNewOrUpdateExistingRolePermissionsInBatches(rolePermissionList);
        List<String> uris = new ArrayList<>();
        rolePermissionList.forEach(rolePermission -> uris.add("/role-permission/info/" + rolePermission.getId()));
        return VO.response(Code.SUCCESS, uris.toString());
    }

    @GetMapping("info")
    public VO<RolePermissionVO> handleGetRolePermissionByIdRequest(
            @RequestParam("id")
            @NotNull(message = "id cannot be null.")
            @Min(value = 1, message = "id must be greater than or equal to 1")
            Integer id
    ) {
        RolePermission rolePermission = rolePermissionBaseService.getRolePermissionById(id);
        RolePermissionVO rolePermissionVO = rolePermissionConverter.toVO(rolePermission);
        return VO.response(Code.SUCCESS, rolePermissionVO);
    }

    @GetMapping("info-list")
    public VO<Page<RolePermissionVO>> handleGetRolePermissionByPageRequest(
            @ModelAttribute RolePermissionDTO rolePermissionDto,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "id:asc") List<String> sorts
    ) {
        RolePermission rolePermission = rolePermissionConverter.toEntity(rolePermissionDto);
        PageRequest pageRequest = getPageRequest(page, size, sorts);
        Page<RolePermission> rolePermissionPage = rolePermissionBaseService.getRolePermissionsByPage(rolePermission, pageRequest);
        Page<RolePermissionVO> rolePermissionVOPage = rolePermissionPage.map(rolePermissionConverter::toVO);
        return VO.response(Code.SUCCESS, rolePermissionVOPage);
    }

}
