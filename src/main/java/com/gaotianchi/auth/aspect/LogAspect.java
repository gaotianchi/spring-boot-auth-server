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
public class LogAspect {
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

        log.info("请求开始：IP={}, URL={}, 方法名={}, 参数={}",
                request.getRemoteAddr(),
                request.getRequestURI(),
                methodName,
                objectMapper.writeValueAsString(args));

        long startTime = System.currentTimeMillis();
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable ex) {
            log.error("请求异常：方法名={}, 异常信息={}", methodName, ex.getMessage(), ex);
            throw ex;
        }

        long endTime = System.currentTimeMillis();
        log.info("请求结束：方法名={}, 响应={}, 耗时={}ms",
                methodName,
                objectMapper.writeValueAsString(result),
                endTime - startTime);

        return result;
    }
}
