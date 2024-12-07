package com.gaotianchi.auth.rest;

import com.gaotianchi.auth.converter.PermissionConverter;
import com.gaotianchi.auth.dto.PermissionDto;
import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.infrastructure.entity.Permission;
import com.gaotianchi.auth.infrastructure.service.PermissionBaseService;
import com.gaotianchi.auth.vo.PermissionVO;
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

@RestController
@RequestMapping("permission")
@Validated
public class PermissionRest {
    private final PermissionBaseService permissionBaseService;
    private final PermissionConverter permissionConverter;

    public PermissionRest(PermissionBaseService permissionBaseService, PermissionConverter permissionConverter) {
        this.permissionBaseService = permissionBaseService;
        this.permissionConverter = permissionConverter;
    }

    @PostMapping("")
    public VO<String> handleCreatePermissionRequest(
            @RequestBody
            @Validated(PermissionDto.CreatePermission.class)
            PermissionDto permissionDto) {
        Permission Permission = permissionConverter.toEntity(permissionDto);
        permissionBaseService.addNewPermission(Permission);
        return VO.response(Code.SUCCESS, "/permission/info/" + Permission.getId());
    }

    @PostMapping("batch")
    public VO<String> handleCreatePermissionsBatchRequest(
            @RequestBody
            @Validated(PermissionDto.CreatePermission.class)
            List<PermissionDto> permissionDtoList) {
        List<Permission> permissionList = permissionDtoList
                .stream()
                .map(permissionConverter::toEntity)
                .toList();
        permissionBaseService.addNewPermissionsBatch(permissionList);
        List<String> uris = new ArrayList<>();
        permissionList.forEach(permission -> uris.add("/permission/info/" + permission.getId()));
        return VO.response(Code.SUCCESS, uris.toString());
    }

    @DeleteMapping("")
    public VO<Void> handleRemovePermissionByIdRequest(
            @RequestParam("id")
            @NotNull(message = "id cannot be null")
            @Min(value = 1, message = "id must be greater than or equal to 1")
            Integer id) {
        permissionBaseService.removePermissionById(id);
        return VO.response(Code.SUCCESS, null);
    }

    @DeleteMapping("batch")
    public VO<Void> handleRemovePermissionBatchByIdsRequest(
            @RequestParam("ids")
            @NotNull(message = "ids cannot be null")
            @Size(min = 1, message = "At least one id must be provided")
            List<Integer> ids) {
        permissionBaseService.removePermissionsBatchByIds(ids);
        return VO.response(Code.SUCCESS, null);
    }

    @PutMapping("")
    public VO<String> handleUpdatePermissionRequest(
            @RequestBody
            @Validated(PermissionDto.UpdatePermission.class)
            PermissionDto permissionDto) {
        Permission permission = permissionConverter.toEntity(permissionDto);
        permissionBaseService.updatePermissionDetailsById(permission);
        return VO.response(Code.SUCCESS, "/permission/info/" + permission.getId());
    }

    @GetMapping("info/{id}")
    public VO<PermissionVO> handleGetPermissionByIdRequest(
            @PathVariable("id")
            @Min(value = 1, message = "id must be greater than or equal to 1")
            Integer id) {
        Permission permission = permissionBaseService.getPermissionById(id);
        PermissionVO permissionVO = permissionConverter.toVO(permission);
        return VO.response(Code.SUCCESS, permissionVO);
    }

    @GetMapping("info-list")
    public VO<Page<PermissionVO>> handleGetPermissionListRequest(
            @ModelAttribute PermissionDto permissionDto,
            @RequestParam(defaultValue = "0")
            @Min(value = 0, message = "page must be greater than or equal to 0")
            int page,
            @RequestParam(defaultValue = "10")
            @Min(value = 1, message = "size must be greater than or equal to 1")
            int size) {
        Permission permission = permissionConverter.toEntity(permissionDto);
        Page<Permission> permissionPage = permissionBaseService.getPermissionsByPage(permission, PageRequest.of(page, size));
        Page<PermissionVO> permissionVOPage = permissionPage.map(permissionConverter::toVO);
        return VO.response(Code.SUCCESS, permissionVOPage);
    }
}