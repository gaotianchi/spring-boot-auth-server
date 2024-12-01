package com.gaotianchi.auth.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author gaotianchi
 * @since 2024/11/30 22:14
 **/
@Aspect
@Component
public class TestAnnotationImpl {

    @Pointcut("@annotation(com.gaotianchi.auth.annotation.TestAnnotation)")
    private void cut(){
    }

    @Before("cut()")
    public void before(){
        System.out.println("自定义注解前置通知！");
    }
    @After("cut()")
    public void after(){
        System.out.println("自定义注解后置通知！");
    }
}