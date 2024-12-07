package com.gaotianchi.auth.rest;

import com.gaotianchi.auth.converter.UserRoleConverter;
import com.gaotianchi.auth.dto.UserRoleDto;
import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.infrastructure.entity.UserRole;
import com.gaotianchi.auth.infrastructure.service.UserRoleBaseService;
import com.gaotianchi.auth.vo.UserRoleVO;
import com.gaotianchi.auth.vo.VO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user-role")
@Validated
public class UserRoleRest {
    private final UserRoleBaseService userRoleBaseService;
    private final UserRoleConverter userRoleConverter;

    public UserRoleRest(UserRoleBaseService userRoleBaseService, UserRoleConverter userRoleConverter) {
        this.userRoleBaseService = userRoleBaseService;
        this.userRoleConverter = userRoleConverter;
    }

    @PostMapping("")
    public VO<String> handleCreateUserRoleRequest(
            @RequestBody
            @Validated(UserRoleDto.CreateUserRole.class)
            UserRoleDto userRoleDto
    ) {
        UserRole UserRole = userRoleConverter.toEntity(userRoleDto);
        userRoleBaseService.addNewUserRole(UserRole);
        return VO.response(Code.SUCCESS, "/user-role/info/" + UserRole.getId());
    }

    @PostMapping("batch")
    public VO<String> handleCreateUserRolesBatchRequest(
            @RequestBody
            @Validated(UserRoleDto.CreateUserRole.class)
            List<UserRoleDto> userRoleDtoList
    ) {
        List<UserRole> userRoleList = userRoleDtoList
                .stream()
                .map(userRoleConverter::toEntity)
                .toList();
        userRoleBaseService.addNewUserRolesBatch(userRoleList);
        return VO.response(Code.SUCCESS, userRoleList
                .stream()
                .map(userRole -> "/user-role/info/" + userRole.getId())
                .toList()
                .toString());
    }

    @DeleteMapping("")
    public VO<Void> handleRemoveUserRoleByIdRequest(
            @RequestParam("id")
            @NotNull(message = "id cannot be null")
            @Min(value = 1, message = "id must be greater than or equal to 1")
            Integer id) {
        userRoleBaseService.removeUserRoleById(id);
        return VO.response(Code.SUCCESS, null);
    }

    @DeleteMapping("batch")
    public VO<Void> handleRemoveUserRoleBatchByIdsRequest(
            @RequestParam("ids")
            @NotNull(message = "ids cannot be null")
            @Size(min = 1, message = "At least one id must be provided")
            List<Integer> ids) {
        userRoleBaseService.removeUserRolesBatchByIds(ids);
        return VO.response(Code.SUCCESS, null);
    }

    @GetMapping("info/{id}")
    public VO<UserRoleVO> handleGetUserRoleByIdRequest(
            @PathVariable("id")
            @Min(value = 1, message = "id must be greater than or equal to 1")
            Integer id) {
        UserRole userRole = userRoleBaseService.getUserRoleById(id);
        UserRoleVO userRoleVO = userRoleConverter.toVO(userRole);
        return VO.response(Code.SUCCESS, userRoleVO);
    }

    @GetMapping("info-list")
    public VO<Page<UserRoleVO>> handleGetUserRoleListRequest(
            @ModelAttribute UserRoleDto userRoleDto,
            @RequestParam(defaultValue = "0")
            @Min(value = 0, message = "page must be greater than or equal to 0")
            int page,
            @RequestParam(defaultValue = "10")
            @Min(value = 1, message = "size must be greater than or equal to 1")
            int size) {
        UserRole userRole = userRoleConverter.toEntity(userRoleDto);
        Page<UserRole> userRolePage = userRoleBaseService.getUserRolesByPage(userRole, PageRequest.of(page, size));
        Page<UserRoleVO> userVOPage = userRolePage.map(userRoleConverter::toVO);
        return VO.response(Code.SUCCESS, userVOPage);
    }
}