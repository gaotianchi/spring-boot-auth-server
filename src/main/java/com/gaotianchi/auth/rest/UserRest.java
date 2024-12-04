package com.gaotianchi.auth.rest;

import com.gaotianchi.auth.entity.User;
import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.service.UserService;
import com.gaotianchi.auth.vo.UserVo;
import com.gaotianchi.auth.vo.VO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gaotianchi
 * @since 2024/11/28 21:09
 **/
@RestController
@RequestMapping("user")
public class UserRest {

    private final UserService userService;

    public UserRest(UserService userService) {
        this.userService = userService;
    }

    private UserVo toUserVo(User user) {
        return UserVo.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    @GetMapping("info/{id}")
    public VO<UserVo> getInfoById(@PathVariable @NotNull(message = "id 不能为空") @Min(value = 1, message = "id 必须大于等于 1") Integer id) {
        User user = userService.findById(id);

        UserVo userVo = toUserVo(user);
        return VO.response(Code.SUCCESS, userVo);
    }
}
