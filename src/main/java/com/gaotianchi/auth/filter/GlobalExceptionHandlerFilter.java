package com.gaotianchi.auth.filter;

import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.vo.VO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gaotianchi
 * @since 2024/11/23 19:23
 **/
@ControllerAdvice
public class GlobalExceptionHandlerFilter {

    /**
     * 处理参数校验失败的异常（用于 @RequestBody）
     *
     * @param ex MethodArgumentNotValidException
     * @return VO<Map < String, String>> 校验失败响应
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public VO<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return VO.response(Code.INVALID_PARAMETER, errors);
    }

    /**
     * 处理参数校验失败的异常（用于 @RequestParam 或 @PathVariable 等）
     *
     * @param ex ConstraintViolationException
     * @return VO<Map < String, String>> 校验失败响应
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public VO<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        return VO.response(Code.INVALID_PARAMETER, errors);
    }

    /**
     * 处理绑定异常（用于表单数据校验）
     *
     * @param ex BindException
     * @return VO<Map < String, String>> 校验失败响应
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public VO<Map<String, String>> handleBindException(BindException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return VO.response(Code.INVALID_PARAMETER, errors);
    }

    /**
     * Global Exception handler
     *
     * @param e Exception
     * @return java.lang.Object
     * @author gaotianchi
     * @since 2024/11/23 19:32
     **/
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public VO<String> exceptionHandler(Exception e) {
        return VO.response(Code.UNKNOWN_ERROR, e.getLocalizedMessage());
    }
}
