package com.gaotianchi.auth.base.rest;

import com.gaotianchi.auth.base.converter.UserRoleConverter;
import com.gaotianchi.auth.base.dto.UserRoleDTO;
import com.gaotianchi.auth.base.entity.UserRole;
import com.gaotianchi.auth.base.service.UserRoleBaseService;
import com.gaotianchi.auth.base.vo.UserRoleVO;
import com.gaotianchi.auth.base.vo.VO;
import com.gaotianchi.auth.enums.Code;
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
public class UserRoleBaseRest {
    private final UserRoleBaseService userRoleBaseService;
    private final UserRoleConverter userRoleConverter;

    public UserRoleBaseRest(UserRoleBaseService userRoleBaseService, UserRoleConverter userRoleConverter) {
        this.userRoleBaseService = userRoleBaseService;
        this.userRoleConverter = userRoleConverter;
    }

    @PostMapping("")
    public VO<String> handleAddNewUserRoleRequest(
            @RequestBody
            @Validated(UserRoleDTO.CreateUserRole.class)
            UserRoleDTO userRoleDto
    ) {
        UserRole UserRole = userRoleConverter.toEntity(userRoleDto);
        userRoleBaseService.addNewUserRole(UserRole);
        return VO.response(Code.SUCCESS, "/user-role/info/" + UserRole.getId());
    }

    @PostMapping("batch")
    public VO<String> handleAddNewUserRolesInBatchesRequest(
            @RequestBody
            @Validated(UserRoleDTO.CreateUserRole.class)
            List<UserRoleDTO> userRoleDTOList
    ) {
        List<UserRole> userRoleList = userRoleDTOList
                .stream()
                .map(userRoleConverter::toEntity)
                .toList();
        userRoleBaseService.addNewUserRolesInBatches(userRoleList);
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
    public VO<Void> handleRemoveUserRoleInBatchesByIdsRequest(
            @RequestParam("ids")
            @NotNull(message = "ids cannot be null")
            @Size(min = 1, message = "At least one id must be provided")
            List<Integer> ids) {
        userRoleBaseService.removeUserRolesInBatchesByIds(ids);
        return VO.response(Code.SUCCESS, null);
    }

    @PutMapping("")
    public VO<String> handleUpdateUserRoleRequest(
            @RequestBody
            UserRoleDTO userRoleDto) {
        UserRole userRole = userRoleConverter.toEntity(userRoleDto);
        userRoleBaseService.updateUserRoleById(userRole);
        return VO.response(Code.SUCCESS, "/user-role/info/" + userRole.getId());
    }

    @PutMapping("")
    public VO<String> handleUpdateUserRoleByIdRequest(
            @RequestBody
            UserRoleDTO userRoleDto) {
        UserRole userRole = userRoleConverter.toEntity(userRoleDto);
        userRoleBaseService.updateUserRoleById(userRole);
        return VO.response(Code.SUCCESS, "/user-role/info/" + userRole.getId());
    }

    @PutMapping("batch")
    public VO<String> handleAddNewOrUpdateExistingUserRoleInBatches(
            @RequestBody
            List<UserRoleDTO> userRoleDTOList
    ) {
        List<UserRole> userRoles = userRoleDTOList.stream()
                .map(userRoleConverter::toEntity)
                .toList();
        userRoleBaseService.addNewOrUpdateExistingUserRolesInBatches(userRoles);
        return VO.response(Code.SUCCESS, userRoles
                .stream()
                .map(userRole -> "/user-role/info/" + userRole.getId())
                .toList()
                .toString());
    }


    @GetMapping("info")
    public VO<UserRoleVO> handleGetUserRoleByIdRequest(
            @RequestParam("id")
            @Min(value = 1, message = "id must be greater than or equal to 1")
            Integer id) {
        UserRole userRole = userRoleBaseService.getUserRoleById(id);
        UserRoleVO userRoleVO = userRoleConverter.toVO(userRole);
        return VO.response(Code.SUCCESS, userRoleVO);
    }

    @GetMapping("info-list")
    public VO<Page<UserRoleVO>> handleGetUserRoleByPageRequest(
            @ModelAttribute UserRoleDTO userRoleDto,
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