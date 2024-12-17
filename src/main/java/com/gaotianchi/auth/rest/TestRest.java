package com.gaotianchi.auth.rest;

import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.vo.VO;
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
        return VO.response(Code.SUCCESS, "Hello, " + name);
    }

    @GetMapping("private")
    public VO<String> testPrivate(
            @RequestParam(value = "name", required = false, defaultValue = "gaotianchi") String name
    ) {
        return VO.response(Code.SUCCESS, "Hello, " + name);
    }
}
