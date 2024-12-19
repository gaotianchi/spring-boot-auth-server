package com.gaotianchi.auth.rest;

import com.gaotianchi.auth.converter.UserConverter;
import com.gaotianchi.auth.dto.UserDTO;
import com.gaotianchi.auth.entity.User;
import com.gaotianchi.auth.service.PasswordRelatedService;
import com.gaotianchi.auth.service.UserLoaderService;
import com.gaotianchi.auth.service.UserLoginAndRegisterService;
import com.gaotianchi.auth.vo.UserVO;
import com.gaotianchi.auth.vo.VO;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gaotianchi.auth.utils.RestTool.getPageRequest;

/**
 * Handle user related requests.
 * @author gaotianchi
 * @since 2024/12/14 16:51
 **/
@RestController
@RequestMapping("users")
public class UserRest {

    private final UserLoginAndRegisterService userLoginAndRegisterService;
    private final PasswordRelatedService passwordRelatedService;
    private final UserLoaderService userLoaderService;
    private final UserConverter userConverter;


    public UserRest(UserLoginAndRegisterService userLoginAndRegisterService, PasswordRelatedService passwordRelatedService, UserLoaderService userLoaderService, UserConverter userConverter) {
        this.userLoginAndRegisterService = userLoginAndRegisterService;
        this.passwordRelatedService = passwordRelatedService;
        this.userLoaderService = userLoaderService;
        this.userConverter = userConverter;
    }

    @PostMapping("register")
    public VO<Void> handleRegisterUserRequest(
            @Validated(UserDTO.RegisterUser.class)
            @RequestBody
            UserDTO userDTO
    ) {
        userLoginAndRegisterService.registerUserViaEmailAndSendVerificationCode(userDTO.getEmail(), userDTO.getPassword());
        return VO.success(null);
    }

    @PatchMapping("register/verify-email")
    public VO<Void> handleVerifyEmailRequest(
            @Validated(UserDTO.VerifyEmail.class)
            @RequestBody
            UserDTO userDTO
    ) {
        userLoginAndRegisterService.verifyEmail(userDTO.getEmail(), userDTO.getVerificationCode());
        return VO.success(null);
    }

    @PatchMapping("deregister")
    public VO<Void> handleDeregisterUserRequest() {
        userLoginAndRegisterService.deregisterUserViaEmailAndSendVerificationCode();
        return VO.success(null);
    }

    @PatchMapping("deregister/confirm")
    public VO<Void> handleConfirmDeregisterUserRequest(
            @Validated(UserDTO.ConfirmDeregisterUser.class)
            @RequestBody
            UserDTO userDTO
    ) {
        userLoginAndRegisterService.confirmDeregisterUserViaEmailAndVerificationCode(userDTO.getVerificationCode());
        return VO.success(null);
    }

    @PatchMapping("password/update")
    public VO<Void> handleUpdatePasswordRequest(
            @Validated(UserDTO.UpdatePassword.class)
            @RequestBody
            UserDTO userDTO
    ) {
        passwordRelatedService.updatePassword(userDTO.getPassword(), userDTO.getNewPassword());
        return VO.success(null);
    }

    @PatchMapping("password/retrieve")
    public VO<Void> handleRetrievePasswordRequest(
            @Validated(UserDTO.CheckEmail.class)
            @RequestBody
            UserDTO userDTO
    ) {
        passwordRelatedService.sendResetPasswordEmail(userDTO.getEmail());
        return VO.success(null);
    }

    @PatchMapping("password/reset")
    public VO<Void> handleResetPassword(
            @Validated(UserDTO.ResetPassword.class)
            @RequestBody
            UserDTO userDTO
    ) {
        passwordRelatedService.resetPassword(userDTO.getEmail(), userDTO.getVerificationCode(), userDTO.getNewPassword());
        return VO.success(null);
    }

    @GetMapping("list")
    public VO<Page<UserVO>> handleGetUsersByPageRequest(
            @ModelAttribute UserDTO userDto,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "id:asc") List<String> sorts
    ) {
        User user = userConverter.toEntity(userDto);
        PageRequest pageRequest = getPageRequest(page, size, sorts);
        Page<User> userPage = userLoaderService.getUsersByPage(user, pageRequest);
        Page<UserVO> userVOPage = userPage.map(userConverter::toVO);
        return VO.success(userVOPage);
    }

    @GetMapping("users-with-certain-roles")
    public VO<Page<UserVO>> handleGetUsersWithCertainRolesRequest(
            @RequestParam("roleIds")
            @NotNull(message = "Role ids cannot be null.")
            List<Integer> roleCodes,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "id:asc") List<String> sorts
    ) {
        PageRequest pageRequest = getPageRequest(page, size, sorts);
        Page<User> userPage = userLoaderService.getUsersWithCertainRoles(roleCodes, pageRequest);
        Page<UserVO> userVOPage = userPage.map(userConverter::toVO);
        return VO.success(userVOPage);
    }
}
