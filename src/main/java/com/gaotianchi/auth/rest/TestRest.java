package com.gaotianchi.auth.rest;


import com.gaotianchi.auth.annotation.TestAnnotation;
import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.vo.VO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gaotianchi
 * @since 2024/11/23 19:51
 **/
@RestController
@RequestMapping("test")
public class TestRest {

    @TestAnnotation
    @GetMapping("/public/{param}")
    public VO<String> testPublic(@PathVariable @NotBlank(message = "参数不能为空") @NotNull String param) {
        return VO.response(Code.SUCCESS, "public " + param);
    }

    @GetMapping("/private/{param}")
    public VO<String> testPrivate(@PathVariable @NotBlank(message = "参数不能为空") @NotNull String param) {
        return VO.response(Code.SUCCESS, "private " + param);
    }
}
