package com.gaotianchi.auth.aspect;

import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.exception.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author gaotianchi
 * @since 2024/12/10 10:42
 **/
@Aspect
@Component
@Slf4j
public class DaoExceptionHandlerAspect {


    @Pointcut("execution(* com.gaotianchi.auth.dao.base.*.insert*(..))")
    public void insertPointcut() {
    }

    @Around("insertPointcut()")
    public Object handleInsertException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (DataIntegrityViolationException ex) {
            if (ex instanceof DuplicateKeyException) {
                throw new SQLException(Code.SQL_DUPLICATE_KEY_ERROR, ex.getMessage());
            } else if (ex.getCause() instanceof SQLIntegrityConstraintViolationException) {
                throw new SQLException(Code.SQL_FOREIGN_KEY_CONSTRAINT_ERROR, ex.getMessage());
            } else {
                throw new SQLException(Code.UNKNOWN_ERROR, ex.getMessage());
            }
        }
    }

}
