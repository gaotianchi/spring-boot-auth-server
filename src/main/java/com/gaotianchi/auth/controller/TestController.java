package com.gaotianchi.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author gaotianchi
 * @since 2024/12/4 8:19
 **/
@Controller
@RequestMapping("test")
public class TestController {
    @GetMapping("public/hello")
    public String hello() {
        return "hello";
    }
}
