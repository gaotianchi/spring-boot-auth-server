package com.gaotianchi.auth.rest;

import com.gaotianchi.auth.dto.UserDTO;
import com.gaotianchi.auth.service.PasswordRelatedService;
import com.gaotianchi.auth.service.UserLoginAndRegisterService;
import com.gaotianchi.auth.vo.VO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    public UserRest(UserLoginAndRegisterService userLoginAndRegisterService, PasswordRelatedService passwordRelatedService) {
        this.userLoginAndRegisterService = userLoginAndRegisterService;
        this.passwordRelatedService = passwordRelatedService;
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
}
