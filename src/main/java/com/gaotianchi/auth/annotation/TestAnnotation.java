package com.gaotianchi.auth.annotation;

import java.lang.annotation.*;

/**
 * @author gaotianchi
 * @since 2024/11/30 22:13
 **/

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {
}
