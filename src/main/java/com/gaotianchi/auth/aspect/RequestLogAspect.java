package com.gaotianchi.auth.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 * @author gaotianchi
 * @since 2024/11/29 9:53
 **/
@Aspect
@Component
@Slf4j
public class RequestLogAspect {
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 定义切点，匹配所有控制器方法
     */
    @Pointcut("execution(* com.gaotianchi.auth.rest..*(..))")
    public void controllerMethods() {
    }

    @Around("controllerMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.info("Request start: IP={}, URL={}, method={}, parameters={}",
                request.getRemoteAddr().replace('\n', '_').replace('\r', '_'),
                request.getRequestURI().replace('\n', '_').replace('\r', '_'),
                methodName,
                objectMapper.writeValueAsString(args).replace('\n', '_').replace('\r', '_'));

        long startTime = System.currentTimeMillis();
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable ex) {
            log.error("Request error: method={}, errors={}", methodName, ex.getMessage(), ex);
            throw ex;
        }

        long endTime = System.currentTimeMillis();
        log.info("Request end: method={}, response={}, time-consuming={}ms",
                methodName,
                objectMapper.writeValueAsString(result),
                endTime - startTime);

        return result;
    }
}
