package com.gaotianchi.auth.rest;

import com.gaotianchi.auth.converter.RolePermissionConverter;
import com.gaotianchi.auth.dto.RolePermissionDto;
import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.infrastructure.entity.RolePermission;
import com.gaotianchi.auth.infrastructure.service.RolePermissionBaseService;
import com.gaotianchi.auth.vo.RolePermissionVO;
import com.gaotianchi.auth.vo.VO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public VO<String> handleCreateRolePermissionRequest(
            @RequestBody
            @Validated(RolePermissionDto.CreateRolePermission.class)
            RolePermissionDto rolePermissionDto
    ) {
        RolePermission rolePermission = rolePermissionConverter.toEntity(rolePermissionDto);
        rolePermissionBaseService.addNewRolePermission(rolePermission);
        return VO.response(Code.SUCCESS, "/role-permission/info/" + rolePermission.getId());
    }

    @PostMapping("batch")
    public VO<String> handleCreateRolePermissionBatchRequest(
            @RequestBody
            @Validated(RolePermissionDto.CreateRolePermission.class)
            List<RolePermissionDto> rolePermissionDtoList
    ) {
        List<RolePermission> rolePermissionList = rolePermissionDtoList
                .stream()
                .map(rolePermissionConverter::toEntity)
                .toList();
        rolePermissionBaseService.addNewRolePermissionsBatch(rolePermissionList);
        List<String> uris = new ArrayList<>();
        rolePermissionList.forEach(rolePermission -> uris.add("/role-permission/info/" + rolePermission.getId()));
        return VO.response(Code.SUCCESS, uris.toString());
    }

    @DeleteMapping("")
    public VO<Void> handleRemoveRolePermissionByIdRequest(
            @RequestParam("id")
            @NotNull(message = "id 不能为空")
            @Min(value = 1, message = "id 必须大于等于 1")
            Integer id
    ) {
        rolePermissionBaseService.removeRolePermissionById(id);
        return VO.response(Code.SUCCESS, null);
    }

    @DeleteMapping("batch")
    public VO<Void> handleRemoveRolePermissionBatchRequest(
            @RequestBody
            @NotNull(message = "ids 不能为空")
            @Size(min = 1, message = "id 数组长度必须大于等于 1")
            List<@NotNull(message = "id 不能为空") Integer> ids
    ) {
        rolePermissionBaseService.removeRolePermissionsBatchByIds(ids);
        return VO.response(Code.SUCCESS, null);
    }

    @PutMapping("")
    public VO<String> handleUpdateRolePermissionRequest(
            @RequestBody
            @Validated(RolePermissionDto.UpdateRolePermission.class)
            RolePermissionDto rolePermissionDto
    ) {
        RolePermission rolePermission = rolePermissionConverter.toEntity(rolePermissionDto);
        rolePermissionBaseService.addNewRolePermission(rolePermission);
        return VO.response(Code.SUCCESS, "/role-permission/info/" + rolePermission.getId());
    }

    @PutMapping("batch")
    public VO<String> handleUpdateRolePermissionBatchRequest(
            @RequestBody
            @Validated(RolePermissionDto.UpdateRolePermission.class)
            List<RolePermissionDto> rolePermissionDtoList
    ) {
        List<RolePermission> rolePermissionList = rolePermissionDtoList
                .stream()
                .map(rolePermissionConverter::toEntity)
                .toList();
        rolePermissionBaseService.addNewOrUpdateRolePermissionsBatch(rolePermissionList);
        List<String> uris = new ArrayList<>();
        rolePermissionList.forEach(rolePermission -> uris.add("/role-permission/info/" + rolePermission.getId()));
        return VO.response(Code.SUCCESS, uris.toString());
    }

    @GetMapping("info/{id}")
    public VO<RolePermissionVO> handleGetRolePermissionByIdRequest(
            @PathVariable
            @NotNull(message = "id 不能为空")
            @Min(value = 1, message = "id 必须大于等于 1")
            Integer id
    ) {
        RolePermission rolePermission = rolePermissionBaseService.getRolePermissionById(id);
        RolePermissionVO rolePermissionVO = rolePermissionConverter.toVO(rolePermission);
        return VO.response(Code.SUCCESS, rolePermissionVO);
    }

    @GetMapping("info-list")
    public VO<Page<RolePermissionVO>> handleGetRolePermissionListRequest(
            @ModelAttribute RolePermissionDto rolePermissionDto,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        RolePermission rolePermission = rolePermissionConverter.toEntity(rolePermissionDto);
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<RolePermission> rolePermissionPage = rolePermissionBaseService.getRolePermissionsByPage(rolePermission, pageRequest);
        Page<RolePermissionVO> rolePermissionVOPage = rolePermissionPage.map(rolePermissionConverter::toVO);
        return VO.response(Code.SUCCESS, rolePermissionVOPage);
    }

}
