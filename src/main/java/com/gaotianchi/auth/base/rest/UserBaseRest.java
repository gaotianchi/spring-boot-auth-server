package com.gaotianchi.auth.base.rest;

import com.gaotianchi.auth.base.converter.UserConverter;
import com.gaotianchi.auth.base.dto.UserDTO;
import com.gaotianchi.auth.base.entity.User;
import com.gaotianchi.auth.base.service.UserBaseService;
import com.gaotianchi.auth.base.vo.UserVO;
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
 * @since 2024/12/07 12:51
 **/
@RestController
@RequestMapping("user")
public class UserBaseRest {

    private final UserBaseService userBaseService;
    private final UserConverter userConverter;

    public UserBaseRest(UserBaseService userBaseService, UserConverter userConverter) {
        this.userBaseService = userBaseService;
        this.userConverter = userConverter;
    }

    @PostMapping("")
    public VO<String> handleAddNewUserRequest(
            @RequestBody
            @Validated(UserDTO.CreateUser.class)
            UserDTO userDto
    ) {
        User user = userConverter.toEntity(userDto);
        userBaseService.addNewUser(user);
        return VO.response(Code.SUCCESS, "/user/info/" + user.getId());
    }

    @PostMapping("batch")
    public VO<String> handleAddNewUserInBatchesRequest(
            @RequestBody
            @Validated(UserDTO.CreateUser.class)
            List<UserDTO> userDTOList
    ) {
        List<User> userList = userDTOList
                .stream()
                .map(userConverter::toEntity)
                .toList();
        userBaseService.addNewUsersInBatches(userList);
        List<String> uris = new ArrayList<>();
        userList.forEach(user -> uris.add("/user/info/" + user.getId()));
        return VO.response(Code.SUCCESS, uris.toString());
    }

    @DeleteMapping("")
    public VO<Void> handleRemoveUserByIdRequest(
            @RequestParam("id")
            @NotNull(message = "id cannot be empty")
            @Min(value = 1, message = "id must be greater then or equal to 1")
            Integer id
    ) {
        userBaseService.removeUserById(id);
        return VO.response(Code.SUCCESS, null);
    }

    @DeleteMapping("batch")
    public VO<Void> handleRemoveUsersInBatchesByIdsRequest(
            @RequestParam("id")
            @NotNull(message = "id cannot be null.")
            @Size(min = 1, message = "At least one id is required")
            List<Integer> ids
    ) {
        userBaseService.removeUsersInBatchesByIds(ids);
        return VO.response(Code.SUCCESS, null);
    }


    @PutMapping("")
    public VO<Void> handleUpdateUserByIdRequest(
            @RequestBody
            @Validated(UserDTO.UpdateUser.class)
            UserDTO userDto
    ) {
        User user = userConverter.toEntity(userDto);
        userBaseService.updateUserById(user);
        return VO.response(Code.SUCCESS, null);
    }

    @PutMapping("batch")
    public VO<Void> handleAddNewOrUpdateExistingUsersInBatchesRequest(
            @RequestBody
            @Validated(UserDTO.UpdateUser.class)
            List<UserDTO> userDTOList
    ) {
        List<User> userList = userDTOList
                .stream()
                .map(userConverter::toEntity)
                .toList();
        userBaseService.addNewOrUpdateExistingUsersInBatches(userList);
        return VO.response(Code.SUCCESS, null);
    }

    @GetMapping("info")
    public VO<UserVO> handleGetUserByIdRequest(
            @RequestParam("id")
            @Min(value = 1, message = "id must be greater then or equal to 1")
            Integer id
    ) {
        User user = userBaseService.getUserById(id);
        UserVO userVO = userConverter.toVO(user);
        return VO.response(Code.SUCCESS, userVO);
    }

    @GetMapping("info-list")
    public VO<Page<UserVO>> handleGetUserByPageRequest(
            @ModelAttribute UserDTO userDto,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "id:asc") List<String> sorts
    ) {
        User user = userConverter.toEntity(userDto);
        PageRequest pageRequest = getPageRequest(page, size, sorts);
        Page<User> userPage = userBaseService.getUsersByPage(user, pageRequest);
        Page<UserVO> userVOPage = userPage.map(userConverter::toVO);
        return VO.response(Code.SUCCESS, userVOPage);
    }
}
