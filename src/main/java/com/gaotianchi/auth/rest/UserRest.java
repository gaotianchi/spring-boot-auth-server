package com.gaotianchi.auth.rest;

import com.gaotianchi.auth.converter.UserConverter;
import com.gaotianchi.auth.dto.UserDto;
import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.infrastructure.entity.User;
import com.gaotianchi.auth.infrastructure.service.UserBaseService;
import com.gaotianchi.auth.vo.UserVO;
import com.gaotianchi.auth.vo.VO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaotianchi
 * @since 2024/11/26 12:51
 **/
@RestController
@RequestMapping("user")
public class UserRest {

    private final UserBaseService userBaseService;
    private final UserConverter userConverter;

    public UserRest(UserBaseService userBaseService, UserConverter userConverter) {
        this.userBaseService = userBaseService;
        this.userConverter = userConverter;
    }

    @PostMapping("")
    public VO<String> handleCreateUserRequest(
            @RequestBody
            @Validated(UserDto.CreateUser.class)
            UserDto userDto
    ) {
        User user = userConverter.toEntity(userDto);
        userBaseService.addNewUser(user);
        return VO.response(Code.SUCCESS, "/user/info/" + user.getId());
    }

    @PostMapping("batch")
    public VO<String> handleCreateUsersBatchRequest(
            @RequestBody
            @Validated(UserDto.CreateUser.class)
            List<UserDto> userDtoList
    ) {
        List<User> userList = userDtoList
                .stream()
                .map(userConverter::toEntity)
                .toList();
        userBaseService.addNewUsersBatch(userList);
        List<String> uris = new ArrayList<>();
        userList.forEach(user -> uris.add("/user/info/" + user.getId()));
        return VO.response(Code.SUCCESS, uris.toString());
    }

    @DeleteMapping("")
    public VO<Void> handleRemoveUserByIdRequest(
            @RequestParam("id")
            @NotNull(message = "id 不能为空")
            @Min(value = 1, message = "id 必须大于等于 1")
            Integer id
    ) {
        userBaseService.removeUserById(id);
        return VO.response(Code.SUCCESS, null);
    }

    @PutMapping("")
    public VO<Void> handleUpdateUserRequest(
            @RequestBody
            @Validated(UserDto.UpdateUser.class)
            UserDto userDto
    ) {
        User user = userConverter.toEntity(userDto);
        userBaseService.updateUserDetailsById(user);
        return VO.response(Code.SUCCESS, null);
    }

    @GetMapping("info/{id}")
    public VO<UserVO> handleGetUserByIdRequest(
            @PathVariable("id")
            @Min(value = 1, message = "id 必须大于等于 1")
            Integer id
    ) {
        User user = userBaseService.getUserById(id);
        UserVO userVO = userConverter.toVO(user);
        return VO.response(Code.SUCCESS, userVO);
    }

    @GetMapping("info-list")
    public VO<Page<UserVO>> handleGetUserListRequest(
            @ModelAttribute UserDto userDto,
            @RequestParam(value = "page", defaultValue = "0")
            @Min(value = 0, message = "page 必须大于等于 0")
            Integer page,
            @RequestParam(value = "size", defaultValue = "10")
            @Min(value = 1, message = "size 必须大于等于 1")
            Integer size
    ) {
        User user = userConverter.toEntity(userDto);
        Page<User> userPage = userBaseService.getUsersByPage(user, PageRequest.of(page, size));
        Page<UserVO> userVOPage = userPage.map(userConverter::toVO);
        return VO.response(Code.SUCCESS, userVOPage);
    }
}
