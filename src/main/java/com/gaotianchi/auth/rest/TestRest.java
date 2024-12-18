package com.gaotianchi.auth.rest;

import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.vo.VO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gaotianchi
 * @since 2024/12/16 18:36
 **/
@RestController
@RequestMapping("test")
public class TestRest {

    @GetMapping("public")
    public VO<String> testPublic(
            @RequestParam(value = "name", required = false, defaultValue = "gaotianchi") String name
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return VO.response(Code.SUCCESS, "Hello, " + name);
    }

    @GetMapping("private")
    public VO<Object> testPrivate(
            @RequestParam(value = "name", required = false, defaultValue = "gaotianchi") String name
    ) {
        // 获取当前的 Authentication 对象
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();

        return VO.response(Code.SUCCESS, jwt.getSubject());
    }

}
