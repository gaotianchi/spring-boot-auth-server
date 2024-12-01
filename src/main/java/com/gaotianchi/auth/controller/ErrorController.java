package com.gaotianchi.auth.controller;


import com.gaotianchi.auth.vo.ErrorVo;
import org.springframework.stereotype.Controller;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.Map;

/**
 * @author gaotianchi
 * @since 2024/12/1 13:32
 **/
@Controller
@RequestMapping("error")
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    private final ErrorAttributes errorAttributes;

    public ErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("")
    public String  handleError(WebRequest webRequest, Model model) {
        Map<String, Object> attributes = errorAttributes.getErrorAttributes(
                webRequest, ErrorAttributeOptions.defaults());
        ErrorVo errorVo = ErrorVo.builder()
                .path(attributes.get("path").toString())
                .message(attributes.get("error").toString())
                .status((Integer) attributes.get("status"))
                .timestamp((Date) attributes.get("timestamp"))
                .build();
        model.addAttribute(errorVo);
        return "/error/error";
    }
}
