package com.gaotianchi.auth.aspect;

/**
 * @author gaotianchi
 * @since 2024/12/10 10:42
 **/
//@Aspect
//@Component
//@Slf4j
public class DaoExceptionHandlerAspect {

    /**
     * Define a facet for each operation involving inserting rows in
     * com.gaotianchi.auth.service.impl.infrastructure.dao (methods starting with insert)
     * to capture SQL errors that may occur during insert.
     * @author gaotianchi
     * @since 2024/12/10 10:50
     **/
//    @Pointcut("execution(* com.gaotianchi.auth.service.impl.infrastructure.dao.*.insert*(..))")
//    public void insertPointcut() {
//    }
//
//    @Around("insertPointcut()")
//    public Object handleInsertException(ProceedingJoinPoint joinPoint) throws Throwable {
//        try {
//            log.info("Start insert.");
//            return joinPoint.proceed();
//        } catch (Exception ex) {
//            log.error("Error occurred during insert operation: {}", ex.getMessage());
//            throw ex;
//        }
//    }

}
